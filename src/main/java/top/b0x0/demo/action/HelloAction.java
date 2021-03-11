package top.b0x0.demo.action;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.demo.domain.ResponseEntry;
import top.b0x0.demo.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author TANG
 */
@RestController
public class HelloAction {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ResponseEntry re;

    /**
     * 注解提供了“路由”信息。它告诉Spring任何带有路径“/”的HTTP请求应该映射到home方法
     *
     * @return /
     */
    @RequestMapping("/hello")
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/hello/world", method = {RequestMethod.POST, RequestMethod.GET})
    String home(@RequestParam(required = true, value = "name") String name) {
        return "Welcome " + name + " to ShenZhen !";
    }

    /**
     * 在上面的代码中我们用@RequestMapping 的params 属性指定了三个参数，
     * 这些参数都是针对请求参数而言的，它们分别表示参数param1 的值必须等于value1 ，
     * 参数param2 必须存在，值无所谓，参数param3 必须不存在，
     * 只有当请求/testParams.do 并且满足指定的三个参数条件的时候才能访问到该方法。
     * 所以当请求/testParams.do?param1=value1&param2=value2 的时候能够正确访问到该testParams 方法，
     * 当请求/testParams.do?param1=value1&param2=value2&param3=value3
     * 的时候就不能够正常的访问到该方法，因为在@RequestMapping 的params 参数里面指定了参数param3 是不能存在的。
     *
     * @return /
     */
    @RequestMapping(value = "/testParams", params = {"param1=Eric", "param2", "!param3"})
    public String testParams() {
        return "Welcome " + " Eric" + " to ShenZhen , Eric is " + 12;
    }


    /**
     * 在 application.properties 配置文件中需要吧数据库改为student_db
     *
     * @return
     */
    @RequestMapping("/stuents")
    public String getDbType() {
        String sql = "select * from gradeOne";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Gson gson = new Gson();
//        for (Map<String, Object> map : list) {
//            Set<Entry<String, Object>> entries = map.entrySet( );
//                if(entries != null) {
//                    Iterator<Entry<String, Object>> iterator = entries.iterator( );
//                    while(iterator.hasNext( )) {
//                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
//                    Object key = entry.getKey( );
//                    Object value = entry.getValue();
//                    System.out.println(key+":"+value);
//                }
//            }
//        }
        return gson.toJson(list);
    }

    /**
     * 在 application.properties 配置文件中需要吧数据库改为student_db
     *
     * @return /
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestParam("name") String name) {
        ResponseEntry re = new ResponseEntry();
        String sql;
        if (StringUtil.isEmpty(name)) {
            re.setCode(ResponseEntry.DATA_EXCEPTION);
            re.setMessage("姓名不能为空");
        } else {
            sql = "insert into gradeOne values ( null , 'eric');";
            jdbcTemplate.execute(sql);

        }
        return new Gson().toJson(re);
    }

    /**
     * 在 application.properties 配置文件中需要吧数据库改为student_db
     *
     * @return /
     */
    @RequestMapping(value = "/regist2", method = RequestMethod.POST)
    public String regist2(@RequestParam("name") String name) {
        ResponseEntry re = new ResponseEntry();
        String sql;
        if (StringUtil.isEmpty(name)) {
            re.setCode(ResponseEntry.DATA_EXCEPTION);
            re.setMessage("姓名不能为空");
        } else {
            sql = "insert into gradeOne values ( null , ?);";
//    		jdbcTemplate.execute(sql);
            //需要指定类型
            jdbcTemplate.update(sql, name);
        }
        return new Gson().toJson(re);
    }

    /**
     * 在 application.properties 配置文件中需要吧数据库改为student_db
     *
     * @return /
     */
    @RequestMapping(value = "/regist3", method = RequestMethod.POST)
    public String regist3(@RequestParam("name") String name,
                          @RequestParam("sex") String sex, @RequestParam("age") int age,
                          @RequestParam(value = "tel", defaultValue = "-") String tel) {
        ResponseEntry re = new ResponseEntry();
        String sql;
        if (StringUtil.isEmpty(name)) {
            re.setCode(ResponseEntry.DATA_EXCEPTION);
            re.setMessage("姓名不能为空");
            return new Gson().toJson(re);
        }

        if (StringUtil.isEmpty(sex)) {
            re.setCode(ResponseEntry.DATA_EXCEPTION);
            re.setMessage("性别不能为空");
            return new Gson().toJson(re);
        }

        sql = "insert into gradeTwo (name,sex,age, tel) values ( ?,?,?,?);";
//		jdbcTemplate.execute(sql);
        //需要指定类型
        jdbcTemplate.update(sql, new Object[]{name, sex, age, tel});
//		jdbcTemplate.update(sql, new Object[]{name,sex,age,tel},
//				new int[]{Types.VARCHAR,Types.CHAR,Types.TINYINT,Types.VARCHAR});
        return new Gson().toJson(re);
    }

    /**
     * 在 application.properties 配置文件中需要吧数据库改为samp_db
     *
     * @return
     */
    @RequestMapping(value = "/regist4", method = RequestMethod.POST)
    public String regist4(@RequestParam("name") final String name,
                          @RequestParam("sex") final String sex, @RequestParam("age") final int age,
                          @RequestParam(value = "tel", defaultValue = "-") final String tel) {
        re = new ResponseEntry();
        String sql;
        if (StringUtil.isEmpty(name)) {
            re.setCode(ResponseEntry.DATA_EXCEPTION);
            re.setMessage("姓名不能为空");
            return new Gson().toJson(re);
        }

        if (StringUtil.isEmpty(sex)) {
            re.setCode(ResponseEntry.DATA_EXCEPTION);
            re.setMessage("性别不能为空");
            return new Gson().toJson(re);
        }

        sql = "insert into user_table (name,sex,age, tel) values ( ?,?,?,?)";
//		jdbcTemplate.execute(sql);
        //需要指定类型
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) {
                // TODO Auto-generated method stub
                try {
                    ps.setString(1, name);
                    ps.setString(2, sex);
                    ps.setInt(3, age);
                    ps.setString(4, tel);
                } catch (SQLException e) {
                    re.setCode(ResponseEntry.DATA_EXCEPTION);
                    re.setMessage(e.getMessage());
                }

            }
        });
        return new Gson().toJson(re);
    }


}
