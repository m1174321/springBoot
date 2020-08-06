package com.fh.controller;

import com.fh.common.JsonData;
import com.fh.entity.Product;
import com.fh.entity.ProductVO;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("findProductIsHot")
    public JsonData findProductIsHot(){
        List<ProductVO> productData = productService.findProductIsHot();
        return JsonData.successJsonData(productData);
    }

    @RequestMapping("findProductById")
    public JsonData findProductById(Integer id){
        List<ProductVO> productList = productService.findProductById(id);
        return JsonData.successJsonData(productList);
    }

    @RequestMapping("findProductData")
    public JsonData findProductData(ProductVO productVO){
        List<ProductVO> productList = productService.findProductData(productVO);
        return JsonData.successJsonData(productList);
    }


}
