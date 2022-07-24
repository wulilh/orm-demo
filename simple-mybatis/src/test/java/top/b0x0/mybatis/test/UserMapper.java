package top.b0x0.mybatis.test;

/**
 * @author tlh Created By 2022-07-23 13:06
 **/
public interface UserMapper {
    String getUsernameById(String id);

    String getUsernameByEmail(String email);
}
