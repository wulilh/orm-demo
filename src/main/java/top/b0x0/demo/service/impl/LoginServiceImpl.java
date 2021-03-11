package top.b0x0.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.b0x0.demo.domain.LoginEntity;
import top.b0x0.demo.mapper.ILoginMapper;
import top.b0x0.demo.service.ILoginService;

import java.util.List;


@Service("loginService")
public class LoginServiceImpl implements ILoginService {
    @Autowired
    ILoginMapper loginMapper;

    @Override
    public void insert(LoginEntity loginEntityNew) {
        loginMapper.insert(loginEntityNew);
    }


    @Override
    public LoginEntity select(String token) {
        return loginMapper.select(token);
    }

    @Override
    public void update(LoginEntity entity) {
        loginMapper.update(entity);
    }

    @Override
    public int delete(String token) {
        return loginMapper.delete(token);
    }

    @Override
    public List<LoginEntity> getAll() {
        return loginMapper.getAll();
    }


}




