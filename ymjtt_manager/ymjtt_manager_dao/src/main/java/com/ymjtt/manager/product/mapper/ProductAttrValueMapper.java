package com.ymjtt.manager.product.mapper;

import com.ymjtt.manager.product.xdo.ProductAttrValueDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/19 23:59
 */
@Repository
public interface ProductAttrValueMapper {

    /* Base */
    List<ProductAttrValueDo> listDO(ProductAttrValueDo productAttrValueDo);

    ProductAttrValueDo getDO(@Param("id") Long id);

    @Delete("delete from product_attr_value where product_attr_value_id = #{id}")            //返回数据类型可以是int也可以是boolean
    boolean removeDO(@Param("id") Long id);

    @Insert("insert into product_attr_value values (null, #{productAttrValueV}, #{productAttrId}, #{valueType}, #{attrParam}, timestamp(now()), 'ywx', timestamp(now()), 'ywx', '1')")
    boolean saveDO(ProductAttrValueDo productAttrValueDo);

    boolean updateDO(ProductAttrValueDo productAttrValueDo);

    /* Others */
    @Delete("DELETE FROM product_attr_value where product_attr_id = #{productAttrId}")
    boolean removeList(Long productAttrId);
}
