package top.b0x0.mybatis;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.b0x0.mybatis.binding.MapperRegistry;
import top.b0x0.mybatis.session.SqlSession;
import top.b0x0.mybatis.session.SqlSessionFactory;
import top.b0x0.mybatis.session.defaults.DefaultSqlSessionFactory;
import top.b0x0.mybatis.test.mapper.UserDetailMapper;
import top.b0x0.mybatis.test.mapper.UserMapper;

import java.util.Map;

public class MapperTests {
    private static final Logger log = LoggerFactory.getLogger(MapperTests.class);

    @Test
    public void test_proxy() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry("top.b0x0.mybatis.test");
//        registry.scanMapperPackage("top.b0x0.mybatis.test");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);

        // 4. 测试验证
        String res = userDao.getUsernameById("10001");
        log.info("测试结果：{}", res);

        UserDetailMapper userDetailMapper = sqlSession.getMapper(UserDetailMapper.class);
        Map<String, Object> userInfo = userDetailMapper.getUserInfo("H01", "zhangsan", "123456");
        System.out.println("userInfo = " + userInfo);

    }

}
