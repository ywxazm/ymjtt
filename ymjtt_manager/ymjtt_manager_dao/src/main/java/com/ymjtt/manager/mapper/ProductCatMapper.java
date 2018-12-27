package com.ymjtt.manager.mapper;

import com.ymjtt.manager.xdo.ProductCatDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/21 9:55
 **/
public interface ProductCatMapper {

    /**
     * 父ID查询
     * @author  ywx
     * @date    2018/12/19 20:45
     * @param   [pid, productCatName]
     * @return  java.util.List<ProductCatDo>
     */
    List<ProductCatDo> listByPid(@Param("pid") Long pid, @Param("productCatName") String productCatName);

    /**
     * ID查询
     * @author  ywx
     * @date    2018/11/28 16:47
     * @param   [productCatId]
     * @return  ProductCatDo
     */
    ProductCatDo getById(@Param("id") Long id);

    /**
     * 名称查询
     * @author  ywx
     * @date    2018/12/20 10:13
     * @param   [productCatName]
     * @return  java.util.List<ProductCatDo>
     */
    List<ProductCatDo> getByName(@Param("productCatName") String productCatName);

    Boolean update(ProductCatDo productCatDo);

    @Insert("insert into product_cat values(#{productCatId}, #{productCatName}, #{productCatStatus}, #{sortOrder}" +
            ", #{parentId}, #{parentCat}, null, null, 1)")
    Boolean add(ProductCatDo productCatDo);

    @Delete("delete from product_cat where product_cat_id = #{id}")
    Boolean remove(Long id);

}
