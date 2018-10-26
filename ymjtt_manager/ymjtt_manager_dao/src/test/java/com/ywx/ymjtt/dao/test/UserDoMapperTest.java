package com.ywx.ymjtt.dao.test;

import com.ywx.ymjtt.pojo.test.OrdersDo;
import com.ywx.ymjtt.pojo.test.UserDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext_*.xml"})
public class UserDoMapperTest {

    @Autowired
    private UserDoMapper userDoMapper;

    //如果POJO没有实现Serializable接口,对于读取与写入的影响
    @Test
    public void test01() {      //无影响
        System.out.println(userDoMapper.queryAll());
    }
    @Test
    public void test02() {      //无影响
        UserDo userDo = new UserDo();
        userDo.setUid(null);
        userDo.setUname("明哥");
        userDoMapper.add(userDo);
    }           //综上,怀疑全部成功的原因在于: 并没有以流的形式将UserDo对象,向数据库进行传输,而是拼装成sql语句,向数据库进行传输的

    //一对多的查询方式
    @Test
    public void test03() {
        UserDo userDo = userDoMapper.queryOrdersByUserId(2);
        System.out.println(userDo);
    }

    //一对一的查询方式
    @Test
    public void test04() {
        OrdersDo ordersDo = userDoMapper.queryOrdersByOrderId(3);
        System.out.println(ordersDo);
    }

    //返回的数据不进行组装至POJO, 而是放在Map中
    @Test
    public void test05() {
        Map<String, Object> map = userDoMapper.queryDate2Map(3);
        System.out.println(map);
    }
}
