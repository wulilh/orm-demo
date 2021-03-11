package top.b0x0.demo.service;

import top.b0x0.demo.domain.LoginEntity;

import java.util.List;

public interface ILoginService {
	
	void insert(LoginEntity loginEntityNew);
	
	LoginEntity select(String token);
	
	void update(LoginEntity entity);
	
	int delete(String token);
	
	/**
	 * 获取数据列表
	 * @return 数据列表
	 */
	List<LoginEntity> getAll();
	
}