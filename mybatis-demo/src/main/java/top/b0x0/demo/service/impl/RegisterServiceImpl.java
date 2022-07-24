package top.b0x0.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.b0x0.demo.domain.LoginEntity;
import top.b0x0.demo.mapper.IRegisterMapper;
import top.b0x0.demo.service.IRegisterService;

import java.util.List;

@Service("registerService")
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    IRegisterMapper registerMapper;

    @Override
    public int register(LoginEntity entity) {
        return registerMapper.register(entity);
    }

    @Override
    public int registerBatch(List<LoginEntity> list) {
        //  insert into table_user_login (phone, password, token)
        // VALUES ('1111','123456','1111') , ('2222','1111','2222') , ('3333','123456','3333') , ('4444','123456','4444');
        return registerMapper.registerBatch(list);
    }

    @Override
    public int updateBatch(List<LoginEntity> list) {
        // update table_user_login set password = case when phone = '1111' then '123' when phone = '2222' then '123' when phone = '3333' then '123' end
        // WHERE phone = '1111' or phone = '2222' or phone = '3333';
        return registerMapper.updateBatch(list);
    }

    @Override
    public int updateBatch2(List<LoginEntity> list) {
        // update table_user_login set password = '2222'
        // WHERE phone = '1111' ; update table_user_login set password = '123'
        // WHERE phone = '2222' ; update table_user_login set password = '666', token = '666'
        // WHERE phone = '3333';
        return registerMapper.updateBatch2(list);
    }

    @Override
    public int deleteBatch(List<LoginEntity> list) {
        return registerMapper.deleteBatch(list);
    }


}
