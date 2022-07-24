package top.b0x0.mybatis.builder;

import top.b0x0.mybatis.session.Configuration;

/**
 * @author tlh Created By 2022-07-24 23:26
 **/
public class BaseBuilder {
    protected Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
