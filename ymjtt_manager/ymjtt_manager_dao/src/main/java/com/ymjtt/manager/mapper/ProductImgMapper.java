package com.ymjtt.manager.mapper;

import com.ymjtt.manager.xdo.ProductImgDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2018/12/27 16:27
 */
@Repository
public interface ProductImgMapper {

    List<ProductImgDo> listByPid(@Param("productId") Long productId);

    @Insert("insert into product_img values(null, #{productId}, #{img}, #{sortOrder}, timestamp(now()), #{createOper}, timestamp(now()), #{lastupdateOper}, 1)")
    boolean add(ProductImgDo productImgDo);

    @Delete("delete from menu where product_img_id = #{productImgId}")
    boolean remove(@Param("productImgId") Long productImgId);

    @Delete("delete from menu where product_id = #{productId}")
    boolean removeByPid(@Param("productId") Long productId);

    boolean update(ProductImgDo productImgDo);
}
