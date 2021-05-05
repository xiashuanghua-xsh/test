package com.webapp.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.webapp.base.utils.Config;
import com.webapp.domain.dao.UserMapper;
import com.webapp.domain.pojo.User;
import com.webapp.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserMapper userDao;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public List<User> findAllUser(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        return userDao.selectAllUser();
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 保存用户基础信息 --> Redis
	 * @param user
	 */
	public void saveUserInfoToRedis(User user) {
		String userId = user.getId().toString();
		redisTemplate.opsForValue().set(Config.getLoginUserKey(userId), JSONObject.toJSONString(user));
		redisTemplate.expire(Config.getLoginUserKey(userId), Config.USER_INFO_TIMEOUT, TimeUnit.MILLISECONDS);

	}

	/**
	 * 保存用户登录信息 --> Redis
	 * @param userId
	 * @param sessionId
	 */
	public void saveUserLoginInfoToRedis(String userId,String sessionId) {
		JSONObject userLoginInfo = new JSONObject();
		userLoginInfo.put("userId", userId);
		redisTemplate.opsForValue().set(Config.getLoginSessionKey(sessionId), userLoginInfo.toJSONString());
		redisTemplate.expire(Config.getLoginSessionKey(sessionId), Config.LOGIN_INFO_TIMEOUT, TimeUnit.MILLISECONDS);
	}


	/**
	 * 从Redis中获取浏览器当前登录信息   userId
	 * @param session
	 * @return userId
	 */
	public String getCurrentLoginInfo(HttpSession session) {
		Object obj = redisTemplate.opsForValue().get(Config.getLoginSessionKey(session.getId()));
		if(obj == null) {
			return null;
		}else {
			JSONObject jsonObject = JSONObject.parseObject(Objects.toString(obj), JSONObject.class);
			return jsonObject.getString("userId");
		}
	}

	/**
	 * 从Redis中获取用户基本信息
	 * @param userId
	 * @return
	 */

	public User getUserInfo(String userId) {
		Object obj = redisTemplate.opsForValue().get(Config.getLoginUserKey(userId));
		if(obj == null) return null;
		return JSONObject.parseObject(Objects.toString(obj), User.class);
	}

	/**
	 * 删除用户登录信息
	 * @param session
	 */
	public void deleteLoginInfo(HttpSession session) {
		String loginInfoHKey = Config.getLoginSessionKey(session.getId());
		if(redisTemplate.hasKey(loginInfoHKey)) {
			redisTemplate.delete(loginInfoHKey);
			redisTemplate.delete(Config.getLoginSessionKey(session.getId()));
		}
	}



	/**
	 * 删除用户基本信息
	 * @param userId
	 */
	public void deleteUserInfo(String userId) {
		String userInfoHKey = Config.getLoginUserKey(userId);
		if(redisTemplate.hasKey(userInfoHKey)) {
			redisTemplate.delete(userInfoHKey);
			redisTemplate.delete(Config.getLoginUserKey(userId));
		}
	}

	/**
	 * 批量删除缓存
	 * @param userIdList
	 */
	public void deleteTaskInfo(Collection<String> userIdList) {
		if (!CollectionUtils.isEmpty(userIdList)) {
			List<String> userInfoHKeys = new ArrayList<>();
			//List<String> taskFileHKeys = new ArrayList<>();
			userIdList.parallelStream().forEach(userId -> {
				String userInfoHKey = Config.getLoginUserKey(userId);
				//String taskFileHKey = Config.getTaskFileHKey(userId);
				userInfoHKeys.add(userInfoHKey);
				//taskFileHKeys.add(taskFileHKey);
			});
			redisTemplate.delete(userInfoHKeys);
			//redisTemplate.delete(taskFileHKeys);
		}
	}


	/**
	 * 创建索引
	 *
	 * @param collection
	 */
	public void createIndex(String collection) {
		if (!this.mongoTemplate.getCollectionNames().contains(collection)) {
			IndexDefinition userIdIndex = new Index("id", Order.ASCENDING);

			this.mongoTemplate.indexOps(collection).ensureIndex(userIdIndex);
		}
	}

	/**
	 * 保存
	 *
	 * @param
	 * @throws Exception
	 * @throws
	 */
	public void save(User user) throws Exception {
		mongoTemplate.insert(user, user.getId().toString());
	}

	/**
	 * 删除用户数据
	 *

	 * @throws Exception
	 */
	public void del(String userId) throws Exception {

		Query query = Query.query(Criteria.where("userId").is(userId).and("delFlag").is(0));
		//逻辑删除，非物理删除
		Update update = Update.update("delFlag", 1);
		// 通过 userId 删除集合中的数据
		mongoTemplate.updateFirst(query, update, Config.USER_LIST_COLLECTION);
		// 通过 userId 删除key:taskId 的数据
		mongoTemplate.updateFirst(query, update, userId);
	}


	/**
	 * 查询某个标注任务详情
	 *
	 * @return
	 * @throws Exception
	 */
	public Object query(String userId,String idNumber) throws Exception {
		if (userId!=null) {
			Query query = Query.query(Criteria.where("userId").is(userId).and("delFlag").is(0));
			User user = mongoTemplate.findOne(query, User.class, Config.USER_LIST_COLLECTION);
			return user;
		} else {
			Map<String, Object> param = new HashMap();
			param.put("userId", userId);
			param.put("idNumber", idNumber);
			Criteria criteria = Criteria.where("userId").is(param.get("userId"))
					.and("idNumber").is(param.get("idNumber"))
					.and("delFlag").is(0);
			Query query = Query.query(criteria);
			List<User> userList = mongoTemplate.find(query, User.class, Config.USER_LIST_COLLECTION);
			return userList;
		}
	}
}
