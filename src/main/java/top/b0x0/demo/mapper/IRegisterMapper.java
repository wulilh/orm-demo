package top.b0x0.demo.mapper;


import top.b0x0.demo.domain.LoginEntity;

import java.util.List;


/**
 * 注册映射
 *
 * @author TANG
 */
public interface IRegisterMapper extends IBaseMapper {

    /**
     * 插入数据
     *
     * @param entity 注册实体类
     * @return 插入错误返回 -1 插入成功 返回当前行的ID
     */
    int register(LoginEntity entity);


    /**
     * 批量插入数据
     *
     * @param list 注册实体类
     * @return 插入错误返回 -1 插入成功 返回当前行的ID
     */
    int registerBatch(List<LoginEntity> list);

    /**
     * 批量更新数据
     *
     * @param list 注册实体类
     * @return 更新数据影响的行数
     */
    int updateBatch(List<LoginEntity> list);

    /**
     * 批量更新数据 更新多条记录为多个字段为不同的值
     *
     * @param list 注册实体类
     * @return 更新数据影响的行数
     */
    int updateBatch2(List<LoginEntity> list);

    /**
     * 按照一定条件批量删除
     *
     * @param list 用户唯一标识
     * @return 删除失败 返回 0 ，删除成功返回影响的行数
     */
    int deleteBatch(List<LoginEntity> list);
}
