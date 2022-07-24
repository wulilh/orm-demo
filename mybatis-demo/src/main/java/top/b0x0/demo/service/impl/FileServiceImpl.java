package top.b0x0.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.b0x0.demo.domain.FileEntity;
import top.b0x0.demo.service.IFileService;
import top.b0x0.demo.mapper.IFileMapper;


/**
 * 文件业务处理
 *
 * @author TANG
 */
@Service("fileService")
public class FileServiceImpl implements IFileService {

    @Autowired
    IFileMapper fileMapper;

    @Override
    public int insertFile(FileEntity entity) {
        // TODO Auto-generated method stub
        return fileMapper.insertFile(entity);
    }

    @Override
    public FileEntity selectFile(String path) {
        // TODO Auto-generated method stub
        return fileMapper.selectFile(path);
    }


}
