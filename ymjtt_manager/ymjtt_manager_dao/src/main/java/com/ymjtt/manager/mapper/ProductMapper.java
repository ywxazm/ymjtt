package com.ymjtt.manager.mapper;

import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.xdo.ProductDo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @auther ywx
 * @date 2018/11/21 11:14
 **/
@Repository
public interface ProductMapper {

    /**
     * 查
     * @author  ywx
     * @date    2018/12/15 22:57
     * @param   [criteria]
     * @return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> list(@Param("criteria") String criteria);

    /**
     * 查询类别下的产品
     * @author  ywx
     * @date    2018/11/27 23:04
     * @param   [cid]
     * @return  java.util.List<ProductDo>
     */
    List<ProductDo> listByCid(Long cid);

    /**
     * 查
     * @author  ywx
     * @date    2018/11/27 21:35
     * @param   [productDo]
     * @return  java.util.List<ProductDo>
     */
    List<ProductDo> listByDo(ProductDo productDo);

    /**
     * 查
     * @author  ywx
     * @date    2018/11/27 10:55
     * @param   [pid]   商品id
     * @return  ProductDo
     */
    ProductDo getById(Long id);

    /**
     * 查
     * @author  ywx
     * @date    2018/11/27 10:55
     * @param   [pid]   商品id
     * @return  ProductDo
     */
    @Select("SELECT pc.product_cat_id id, pc.product_cat_name name, pc.product_cat_status status " +
            "FROM product p " +
            "LEFT JOIN product_cat pc ON p.cid = pc.product_cat_id " +
            "WHERE p.product_id = #{id}")
    ProductCatNodeVO getCatById(@Param("id") Long id);

    /**
     * 删除
     * @author  ywx
     * @date    2018/11/27 21:47
     * @param   [pid]
     * @return  boolean
     */
    @Delete("delete from product where product_id = #{pid}")            //返回数据类型可以是int也可以是boolean
    boolean remove(String pid);

    /**
     * 删除, 支持批量操作
     * @author  ywx
     * @date    2018/11/27 21:47
     * @param   [pids]
     * @return  void
     */
    int removeList(String pids);

    /**
     * 增
     * @author  ywx
     * @date    2018/11/27 21:48
     * @param   [productDo]
     * @return  void
     */
    @Insert("insert into product values (#{productId}, #{title}, #{sellPoint}, #{price}, #{barcode}, #{image}, #{cid}, 1, null,  #{createOper}, null, 0, #{lastupdateOper})")
    boolean add(ProductDo productDo);

    /**
     * 更新
     * @author  ywx
     * @date    2018/11/27 21:49
     * @param   [productDo]
     * @return  void
     */
    boolean update(ProductDo productDo);

}
