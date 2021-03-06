package com.ymjtt.test.mapper;

import com.ymjtt.test.xdo.OrdersDo;
import com.ymjtt.test.xdo.UserDo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDoMapper {

    //如果POJO没有实现Serializable接口,对于读取与写入的影响
    List<UserDo> listUserDo();
    void saveUserDo(UserDo userDo);

    //一对多的查询方式
    UserDo listOrdersByUserId(int uid);
    //一对一的查询方式
    OrdersDo getOrdersByOrderId(int oid);
    //返回的数据不进行组装至POJO, 而是放在Map中
    Map<String, Object> getDate2Map(int oid);

    //Sql注入
    //成功注入
    List<UserDo> Injection(String uname);
    //无法注入
    List<UserDo> NoInjection(String uname);
}
