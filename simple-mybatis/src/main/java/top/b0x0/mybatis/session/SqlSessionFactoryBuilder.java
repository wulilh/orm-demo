package top.b0x0.mybatis.session;

import top.b0x0.mybatis.builder.xml.XMLBuilder;
import top.b0x0.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @author tlh Created By 2022-07-24 21:37
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(String mapperLocation) throws IOException {
        XMLBuilder xmlBuilder = new XMLBuilder(mapperLocation);
        return build(xmlBuilder.getConfiguration());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
