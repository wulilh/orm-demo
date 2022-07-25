package top.b0x0.mybatis;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.b0x0.mybatis.binding.MapperRegistry;
import top.b0x0.mybatis.session.SqlSession;
import top.b0x0.mybatis.session.SqlSessionFactory;
import top.b0x0.mybatis.session.SqlSessionFactoryBuilder;
import top.b0x0.mybatis.session.defaults.DefaultSqlSessionFactory;
import top.b0x0.mybatis.test.mapper.UserDetailMapper;
import top.b0x0.mybatis.test.mapper.UserMapper;

import java.io.IOException;
import java.util.Map;

public class MapperTests {
    private static final Logger log = LoggerFactory.getLogger(MapperTests.class);

    @Test
    public void test_proxy() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build("mybatis/mapper");

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);

        // 4. 测试验证
        String res = userDao.getUsernameById("10001");
        log.info("测试结果：{}", res);
    }

}
