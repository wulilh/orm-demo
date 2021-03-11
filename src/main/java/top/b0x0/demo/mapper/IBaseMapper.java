package top.b0x0.demo.mapper;


import java.util.List;
import java.util.Map;

/**
 * <p>Title:提供父类常用操作方法 </p>
 */
public interface IBaseMapper {

    /**
     * 按照条件分页获取数据
     *
     * @param t /
     * @return /
     */
    <T> List<Map<String, Object>> getListByPage(T t);

    /**
     * 获取总页数条数，用作分页
     *
     * @param t /
     * @return /
     */
    <T> int getCount(T t);

    /**
     * 获取所有符合条件的列表数据，不做分页
     *
     * @param t  /
     * @return /
     */
    <T> List<Map<String, Object>> getAllDataList(T t);


    /**
     * 获取数据列表
     *
     * @return 数据列表
     */
    <T> List<T> getAll();

    /**
     * 根据主键ID获取单条记录
     *
     * @param id  /
     * @return /
     */
    Map<String, Object> getSingleDataById(String id);

    /**
     * 新增
     *
     * @param t /
     * @return /
     */
    <T> int insert(T t);

    /**
     * 批量执行插入操作
     *
     * @param list /
     * @return /
     */
    <T> int batchInsert(List<T> list);

    /**
     * 修改
     *
     * @param t /
     * @return /
     */
    <T> int update(T t);

    /**
     * 根据主键删除数据
     *
     * @param id /
     * @return /
     */
    int delete(String id);

    /**
     * 查询
     *
     * @param token /
     * @return /
     */
    <T> T select(String token);


}
