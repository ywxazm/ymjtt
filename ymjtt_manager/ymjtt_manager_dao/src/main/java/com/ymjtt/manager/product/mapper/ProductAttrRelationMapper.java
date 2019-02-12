package com.ymjtt.manager.product.mapper;

import com.ymjtt.manager.product.xdo.ProductAttrRelationDo;
import com.ymjtt.manager.product.xdo.ProductDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @auther ywx
 * @date 2018/11/21 11:14
 **/
@Repository
public interface ProductAttrRelationMapper {

    /* Base */
    List<ProductAttrRelationDo> listDO(ProductAttrRelationDo productAttrRelationDo);

    ProductAttrRelationDo getDO(@Param("id") Long id);

    @Delete("delete from product_attr_relation where product_id = #{pid}")
    boolean removeDO(@Param("pid") Long pid);

    @Insert("insert into product_attr_relation values (null, #{productId}, #{productAttrId}, #{productAttrValueId}, #{buildType}, timestamp(now()), 'ywx')")
    boolean saveDO(ProductAttrRelationDo productAttrRelationDo);

    /* Others */
}
