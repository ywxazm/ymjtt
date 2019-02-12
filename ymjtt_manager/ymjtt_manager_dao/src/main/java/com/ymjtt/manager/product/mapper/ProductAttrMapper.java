package com.ymjtt.manager.product.mapper;

import com.ymjtt.manager.product.xdo.ProductAttrDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/19 21:34
 */
@Repository
public interface ProductAttrMapper {

    /* Base */
    List<ProductAttrDo> listDO(ProductAttrDo productAttrDo);

    ProductAttrDo getDO(@Param("id") Long id);

    @Delete("delete from product_attr where product_attr_id = #{id}")
    boolean removeDO(@Param("id") Long id);

    @Insert("insert into product_attr values (null, #{productAttrName}, #{belongId}, #{attrType}, #{sortOrder}, timestamp(now()), 'ywx', timestamp(now()), 'ywx', '1')")
    boolean saveDO(ProductAttrDo productAttrDo);

    boolean updateDO(ProductAttrDo productAttrDo);

    /* Others */

}
