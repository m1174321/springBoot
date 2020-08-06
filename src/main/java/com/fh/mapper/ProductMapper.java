package com.fh.mapper;

import com.fh.entity.Product;
import com.fh.entity.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<ProductVO> findProductIsHot();

    List<ProductVO> findProductById(Integer id);

    List<ProductVO> findProductData(ProductVO productVO);

    Integer findProInventory(Integer id);

    int updateInventory(@Param("id")Integer id,@Param("productcount") Integer productcount);

    Product selectProductById(Integer id);
}
