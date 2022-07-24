package top.b0x0.mybatis.session.defaults;

import top.b0x0.mybatis.binding.MapperRegistry;
import top.b0x0.mybatis.session.SqlSession;
import top.b0x0.mybatis.session.SqlSessionFactory;

/**
 * @author tlh Created By 2022-07-23 23:54
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
