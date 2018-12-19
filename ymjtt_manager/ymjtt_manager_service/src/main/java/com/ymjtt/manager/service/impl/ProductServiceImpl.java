package com.ymjtt.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.common.util.jedis.GenerateID;
import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.mapper.ProductMapper;
import com.ymjtt.manager.service.ProductService;
import com.ymjtt.manager.xdo.ProductDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品服务
 * @author  ywx
 * @date    2018/11/21 15:20
 */
@Service
public class ProductServiceImpl<T> implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private GenerateID generateID;

    /**
     *
     * @author  ywx
     * @date    2018/11/27 10:57
     * @param   [pid] 商品id
     * @return  com.ymjtt.manager.xdo.ProductDo
     */
    @Override
    public ProductDo getById(Long id) {
        logger.debug("the thread id is {}, ProductServiceImpl.getById() param id = {}", Thread.currentThread().getId(), id);
        Long startTime = System.currentTimeMillis();

        ProductDo productDo = productMapper.getById(id);

        logger.debug("the thread id is {}, ProductServiceImpl.getById() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productDo;
    }

    /**
     *
     * @author  ywx
     * @date    2018/11/27 23:13
     * @param   [criteria, currPage, pageSize]
     * @return  com.github.pagehelper.PageInfo<com.ymjtt.manager.xdo.ProductDo>
     */
    @Override
    public PageInfo<List<Map<String, Object>>> list(String criteria, Integer currPage, Integer pageSize) {
        logger.debug("the thread id is {}, ProductServiceImpl.list() param criteria = {}, currPage = {}, pageSize = {}"
                , Thread.currentThread().getId(), criteria, currPage, pageSize);
        Long startTime = System.currentTimeMillis();

        PageHelper.startPage(currPage, pageSize);

        List<Map<String, Object>> list = productMapper.list(criteria);

        PageInfo pageInfo = new PageInfo<>(list);

        logger.debug("the thread id is {}, ProductServiceImpl.list() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return pageInfo;
    }

    /**
     *
     * @author  ywx
     * @date    2018/11/27 22:12
     * @param   [pid]
     * @return  void
     */
    @Override
    public boolean remove(String id) {
        logger.debug("the thread id is {}, ProductServiceImpl.remove() param id = {}", Thread.currentThread().getId(), id);
        Long startTime = System.currentTimeMillis();

        boolean result = productMapper.remove(id);

        logger.debug("the thread id is {}, ProductServiceImpl.remove() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     *
     * @author  ywx
     * @date    2018/11/27 22:12
     * @param   [productDo]
     * @return  boolean
     */
    @Override
    public boolean add(ProductDo productDo) {
        logger.debug("the thread id is {}, ProductServiceImpl.add() param productDo = {}", Thread.currentThread().getId(), productDo);
        Long startTime = System.currentTimeMillis();

        //TODO: 创建者和操作者在构建用户管理后, 应该从请求中获取;
        productDo.setCreateOper("ywx");
        productDo.setLastupdateOper("ywx");
        synchronized (this) {
            productDo.setProductId(Long.parseLong(generateID.generateProductID()));
            productDo.setBarcode(generateID.generateProductBarCode());
        }
        boolean result = productMapper.add(productDo);

        logger.debug("the thread id is {}, ProductServiceImpl.add() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * 只根据ID更新
     * @author  ywx
     * @date    2018/11/27 22:12
     * @param   [productDo]
     * @return  boolean
     */
    @Override
    public boolean update(ProductDo productDo) {
        logger.debug("the thread id is {}, ProductServiceImpl.update() param productDo = {}", Thread.currentThread().getId(), productDo);
        Long startTime = System.currentTimeMillis();

        boolean result = productMapper.update(productDo);

        logger.debug("the thread id is {}, ProductServiceImpl.update() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     *
     * @author  ywx
     * @date    2018/11/27 10:57
     * @param   [cid]   商品类别id
     * @return  java.util.List<com.ymjtt.manager.xdo.ProductDo>
     */
    @Override
    public List<ProductDo> listByCid(Long cid) {
        logger.debug("the thread id is {}, ProductServiceImpl.listByCid() param cid = {}", Thread.currentThread().getId(), cid);
        Long startTime = System.currentTimeMillis();

        List<ProductDo> productDoList = productMapper.listByCid(cid);

        logger.debug("the thread id is {}, ProductServiceImpl.listByCid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productDoList;
    }

    /**
     *
     * @author  ywx
     * @date    2018/11/27 21:38
     * @param   [productDo, currPage, pageSize]
     * @return  com.github.pagehelper.PageInfo<com.ymjtt.manager.xdo.ProductDo>
     */
    @Override
    public PageInfo<ProductDo> listByDo(ProductDo productDo, Integer currPage, Integer pageSize) {
        logger.debug("the thread id is {}, ProductServiceImpl.listByDo() param productDo = {}, currPage = {}, " +
                "pageSize = {}", Thread.currentThread().getId(), productDo, currPage, pageSize);
        Long startTime = System.currentTimeMillis();

        PageHelper.startPage(currPage, pageSize);
        List<ProductDo> productDoList = productMapper.listByDo(productDo);
        PageInfo<ProductDo> pageInfo = new PageInfo<>(productDoList);

        logger.debug("the thread id is {}, ProductServiceImpl.listByDo() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return pageInfo;
    }

    /**
     * 根据商品ID, 查询商品种类
     * @author  ywx
     * @date    2018/11/28 17:00
     * @param   [pid]
     * @return  com.ymjtt.common.vo.ProductCatNodeVO
     */
    @Override
    public ProductCatNodeVO getCatById(Long id) {
        logger.debug("the thread id is {}, ProductServiceImpl.getCatById() param id = {}", Thread.currentThread().getId(), id);
        Long startTime = System.currentTimeMillis();

        ProductCatNodeVO productCatNodeVO = productMapper.getCatById(id);

        logger.debug("the thread id is {}, ProductServiceImpl.getCatById() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productCatNodeVO;
    }

    /**
     * 传入字符串, 以逗号隔开, 支持批量操作
     * @author  ywx
     * @date    2018/11/27 22:12
     * @param   [pids]
     * @return  void
     */
    @Override
    public int removeList(String ids) {
        logger.debug("the thread id is {}, ProductServiceImpl.removeList() param ids = {}", Thread.currentThread().getId(), ids);
        Long startTime = System.currentTimeMillis();

        int count = productMapper.removeList(ids);

        logger.debug("the thread id is {}, ProductServiceImpl.removeList() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return count;
    }




}
