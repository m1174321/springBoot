package com.fh.controller;


import com.fh.Annotion.LogAop;
import com.fh.common.JsonData;
import com.fh.entity.Vip;
import com.fh.service.UserService;
import com.fh.utils.JWT;
import com.fh.utils.OSSUtil;
import com.fh.utils.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //发送短信
    @RequestMapping("smsPhoneCode")
    public JsonData smsPhoneCode(String phone){
        //查看用户输入的手机号或者用户名是否存在数据库中
        Vip vip = userService.findNamePhone(phone);
        if(vip != null){
            //调用阿里云的短信接口  把返回的验证码存入到redis里面
            //String coderr;Num = AliyunSmsUtils.smsUtil(vip.getUserPhone);
            String codeNum = "1111";
            RedisPool.setexRedis(phone+"_wxwuSms",60*30,codeNum);
            return JsonData.successJsonData("发送成功");
        }
        return JsonData.successJsonData("该用户或者该手机号不存在");
    }

    //登录
    @RequestMapping("yanzhengLogin")
    @LogAop("登陆方法")
    public JsonData yanzhengLogin(String phone, String codeNum){
        //根据前台传来的手机号查询redis里面是否存在数据
        String redisPhoneSms = RedisPool.getexRedis(phone + "_wxwuSms");
        //验证查出来的验证码和输入的是否一致
        if(redisPhoneSms.equals(codeNum)){
            //在这里调用这个方法要的是 这条数据的Id
            Vip vip = userService.findNamePhone(phone);
            if(vip != null){
                Map map =new HashMap();
                //实例一个vip对象 用于加密
                Vip vipToken = new Vip();
                vipToken.setId(vip.getId());
                vipToken.setUserPhone(phone);
                //将对象加密 先JWT 在 Base64
                String sign = JWT.sign(vipToken, 1000 * 60 * 60);
                String token = Base64.getEncoder().encodeToString((phone + "," + sign).getBytes());
                RedisPool.setRedis(phone+"_wxwu",token);
                map.put("code",200);
                map.put("message","登陆成功");
                map.put("token",token);
                return JsonData.successJsonData(map);
            }
        }
        return JsonData.errorJsonData("该用户或者该手机号不存在");
    }



    @RequestMapping("/findAreaList")
    public JsonData findAreaList(){
        String areaList = RedisPool.getRedis("areaList");

        return JsonData.successJsonData(areaList);
    }

    @RequestMapping("addVip")
    public JsonData addVip(Vip vip){
        Integer id = userService.addVip(vip);
        RedisPool.hsetRedis("vip_wxwu",id.toString(),vip);
        return JsonData.successJsonData("success");
    }






    //文件上传
    @RequestMapping("OSSuploadFile")
    public Map OSSuploadFile( @RequestParam("img") MultipartFile img) {
        Map map = new HashMap();
        try {
            File file = OSSUtil.readFiles(img);
            String filePath = OSSUtil.uploadFile(file);
            map.put("filePath",filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
