package com.fh.controller;

import com.fh.common.JsonData;
import com.fh.entity.Type;
import com.fh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("findTypesAll")
    @ResponseBody
    public JsonData findTypesAll(){
        List<Type> typeList = typeService.findTypesAll();
        return JsonData.successJsonData(typeList);
    }

}
