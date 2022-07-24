package top.b0x0.mybatis.builder.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.b0x0.mybatis.builder.BaseBuilder;
import top.b0x0.mybatis.io.Resources;
import top.b0x0.mybatis.mapping.MapperStatement;
import top.b0x0.mybatis.mapping.SqlType;
import top.b0x0.mybatis.mapping.XMLTagAttribute;
import top.b0x0.mybatis.session.Configuration;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tlh Created By 2022-07-24 21:51
 **/
public class XMLBuilder extends BaseBuilder {
    /**
     * 匹配SQL中的 #{} 占位符
     */
    private static final Pattern PATTERN_POUND_KEY = Pattern.compile("(#\\{(.*?)})");

    public XMLBuilder(String xmlPackageName) throws IOException {
        // 调用父类初始化Configuration
        super(new Configuration());

        List<Reader> readers = Resources.getResourceAsReaders(xmlPackageName);

        for (Reader reader : readers) {
            SAXReader saxReader = new SAXReader();
            try {
                Document document = saxReader.read(reader);
                Element root = document.getRootElement();
                parseMapperXml(root);
            } catch (Exception e) {
                throw new RuntimeException("Error parsing SQL Mapper XML. Cause: " + e, e);
            }
        }
    }

    public void parseMapperXml(Element root) throws Exception {
        // mapper xml文件命名空间
        String namespace = root.attribute(XMLTagAttribute.namespace.name()).getValue();

        // 所有SQL语句节点
        List<Element> elements = root.elements();
        for (Element element : elements) {

            String sqlTypeStr = element.getName();

            String id = element.attribute(XMLTagAttribute.id.name()).getValue();
            String parameterType = element.attribute(XMLTagAttribute.parameterType.name()).getValue();
            String resultType = element.attribute(XMLTagAttribute.resultType.name()).getValue();

            String sql = element.getText();
            // ? 匹配
            Map<Integer, String> parameter = new HashMap<>();
            Matcher matcher = PATTERN_POUND_KEY.matcher(sql);
            for (int i = 1; matcher.find(); i++) {
                String g1 = matcher.group(1);
                String g2 = matcher.group(2);
                parameter.put(i, g2);
                sql = sql.replace(g1, "?");
            }
            MapperStatement ms = new MapperStatement();
            // sql类型
            ms.setSqlType(SqlType.valueOf(sqlTypeStr.toUpperCase().toUpperCase(Locale.ENGLISH)));
            // sql全限定名
            String msId = namespace + "." + id;
            ms.setId(msId);

            ms.setResultType(resultType);
            ms.setParameterType(parameterType);
            ms.setSql(sql);
            ms.setParameters(parameter);
            // 添加解析 SQL
            configuration.addMappedStatement(ms);
        }

        // 注册Mapper映射器
        configuration.addMapper(Resources.classForName(namespace));
    }

}
