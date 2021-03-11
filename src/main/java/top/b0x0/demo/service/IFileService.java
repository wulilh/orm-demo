package top.b0x0.demo.service;

import top.b0x0.demo.domain.FileEntity;


public interface IFileService {
    /**
     * 保存文件到数据库
     *
     * @param entity
     * @return
     */
    int insertFile(FileEntity entity);


    /**
     * 查找数据库中的文件
     *
     * @param path 文件路径
     * @return
     */
    FileEntity selectFile(String path);


}
