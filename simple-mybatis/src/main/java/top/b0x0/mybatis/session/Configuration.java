package top.b0x0.mybatis.session;

import top.b0x0.mybatis.binding.MapperRegistry;
import top.b0x0.mybatis.mapping.MapperStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tlh Created By 2022-07-24 21:39
 **/
public class Configuration {
    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     * key：mapper方法全限定名 例如: top.b0x0.mybatis.test.mapper.UserMapper.getUsernameById
     * value: 值是解析xml的值对象
     */
    protected final Map<String, MapperStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName) {
        mapperRegistry.scanMapperPackage(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MapperStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MapperStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

}
