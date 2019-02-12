package com.ymjtt.manager.product.mapper;

import com.ymjtt.manager.product.xdo.ProductDetailDo;
import com.ymjtt.manager.product.xdo.ProductDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/17 16:08
 */
@Repository
public interface ProductDetailMapper {

    /* Base */
    List<ProductDetailDo> listDO(ProductDetailDo productDetailDo);

    ProductDetailDo getDO(@Param("id") Long id);

    @Delete("delete from product_detail where product_detail_id = #{id}")
    boolean removeDO(@Param("id") Long id);

    @Insert("insert into product_detail values (null, #{productId}, #{image}, timestamp(now()), 'ywx',timestamp(now()), 'ywx', '1')")
    boolean saveDO(ProductDetailDo productDetailDo);

    boolean updateDO(ProductDetailDo productDetailDo);


    /* Others */
    @Delete("delete from product_detail where product_id = #{parentId}")
    boolean removeByParentId(@Param("parentId") Long parentId);
}
