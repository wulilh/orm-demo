package top.b0x0.demo.mapper;

import top.b0x0.demo.domain.FileEntity;

/**
 * 文件上传、下载映射
 * @author TANG
 *
 */
public interface IFileMapper {
   /**
    * 保存文件到数据库
    * @param entity /
    * @return /
    */
   int insertFile (FileEntity entity);
   
   /**
    * 查找数据库中的文件
    * @param path 图片路径 /
    * @return /
    */
   FileEntity selectFile(String path );
   
   
}
