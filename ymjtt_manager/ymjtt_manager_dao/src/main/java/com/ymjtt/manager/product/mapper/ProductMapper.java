package com.ymjtt.manager.product.mapper;

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
public interface ProductMapper {

    /* Base */
    List<ProductDo> listDO(ProductDo productDo);

    ProductDo getDO(@Param("id") Long id);

    @Delete("delete from product where product_id = #{id}")            //返回数据类型可以是int也可以是boolean
    boolean removeDO(@Param("id") Long id);

    @Insert("insert into product values (#{productId}, #{productName}, #{sellPoint}, #{basePrice}, #{barcode}, #{image}, #{cid}, '1', '0', timestamp(now()), 'ywx',timestamp(now()), 'ywx', '1')")
    boolean saveDO(ProductDo productDo);

    boolean updateDO(ProductDo productDo);

    /* Others */
}
