package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.entity.Area;
import com.fh.entity.Product;
import com.fh.entity.ProductVO;
import com.fh.mapper.ProductMapper;
import com.fh.service.ProductService;
import com.fh.utils.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductVO> findProductIsHot() {
        return productMapper.findProductIsHot();
    }

    @Override
    public List<ProductVO> findProductById(Integer id) {
        List<ProductVO> productVOList = productMapper.findProductById(id);
            String[] split = productVOList.get(0).getAreaIds().split(",");
            if(StringUtils.isEmpty(split) != true){
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < split.length; j++) {
                    String hgetRedis = RedisPool.hgetRedis("area_wxwu", split[j]);
                    Area area = JSONObject.parseObject(hgetRedis, Area.class);
                    sb.append(area.getName()+" ");
                }
                productVOList.get(0).setAreaIds(sb.toString());
            }
        return productVOList;
    }

    @Override
    public List<ProductVO> findProductData(ProductVO productVO) {
        return productMapper.findProductData(productVO);
    }
}
