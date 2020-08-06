package com.fh.service;

import com.fh.entity.Product;
import com.fh.entity.ProductVO;

import java.util.List;

public interface ProductService {
    List<ProductVO> findProductIsHot();

    List<ProductVO> findProductById(Integer id);

    List<ProductVO> findProductData(ProductVO productVO);
}
