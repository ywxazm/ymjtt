package com.ymjtt.manager.product.mapper;

import com.ymjtt.manager.product.xdo.ProductCatDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/21 9:55
 **/
@Repository
public interface ProductCatMapper {

    /* Base */
    List<ProductCatDo> listDO(ProductCatDo productCatDo);

    ProductCatDo getDO(@Param("id") Long id);

    @Delete("delete from product_cat where product_cat_id = #{id}")
    Boolean removeDO(@Param("id") Long id);

    @Insert("insert into product_cat values(#{productCatId}, #{productCatName}, #{productCatStatus}, #{sortOrder}, " +
            "#{parentId}, timestamp(now()), 'ywx',timestamp(now()), 'ywx', '1')")
    Boolean saveDO(ProductCatDo productCatDo);

    Boolean updateDO(ProductCatDo productCatDo);


    /* Others */
    List<ProductCatDo> listByParentId(@Param("parentId") Long parentId);



}
