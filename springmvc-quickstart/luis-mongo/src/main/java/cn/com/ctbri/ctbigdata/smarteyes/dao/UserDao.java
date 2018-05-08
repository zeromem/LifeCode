package cn.com.ctbri.ctbigdata.smarteyes.dao;

import cn.com.ctbri.ctbigdata.smarteyes.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by luyi on 2017/6/21.
 */
public interface UserDao extends MongoBase<User> {
	@Override
	public void insert(User object, String collectionName);

	@Override
	public User findOne(Map<String,Object> params, String collectionName);

	@Override
	public List<User> findAll(Map<String,Object> params, String collectionName);

	@Override
	public void update(Map<String,Object> params, String collectionName);

	@Override
	public void createCollection(String name);

	@Override
	public void remove(Map<String, Object> params,String collectionName);
}
