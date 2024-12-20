package com.fengxin;

import com.fengxin.mapper.CustomerMapper;
import com.fengxin.mapper.OrderMapper;
import com.fengxin.pojo.Customer;
import com.fengxin.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author FENGXIN
 * @date 2024/8/1
 * @project ssm-mybatis-part
 **/
public class MulTest {
    private SqlSession session;
    
    @BeforeEach
    public void before () throws IOException {
        InputStream is = Resources.getResourceAsStream ("mybatis/MybatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder ().build (is);
        session = sqlSessionFactory.openSession (true);
    }
    @Test
    public void testToOne(){
        OrderMapper mapper = session.getMapper (OrderMapper.class);
        Order order = mapper.queryOrderById (1);
        System.out.println ("order = " + order);
    }
    
    @Test
    public void testToMul(){
        CustomerMapper mapper = session.getMapper (CustomerMapper.class);
        List<Customer> customers = mapper.queryCustomerAll ();
        System.out.println ("customers = " + customers);
    }
    
    @AfterEach
    public void after () {
        session.close ();
    }
}