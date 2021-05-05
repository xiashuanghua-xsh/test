package com.webapp.base.utils;

public class Config {
	
    /**
     * 线程名规范化
     */
    public static String THREAD_NAME_FORMAT = "video-download-at-%d";
	
	public static final String LOGIN_USER_SESSION = "login:user:session";
	public static final String LOGIN_USER_INFO = "login:user:info";
	
	public static final String REGIST_SESSION_VCODE = "regist:session:vcode";

	//mongodb中userList库
	public static final String USER_LIST_COLLECTION = "userList";
	
	/**
	 * 登录信息过期时间，与session保持一致为30分钟
	 */
	public static final Long LOGIN_INFO_TIMEOUT = 60*30*1000L;
	
	/**
	 * 用户信息保存在session中，为7天（一周）
	 */
	public static final Long USER_INFO_TIMEOUT = 7*24*3600*1000L;

	public static String getLoginSessionKey(String sessionId) {
		
        return LOGIN_USER_SESSION + ":" + sessionId;
    }
	
	public static String getLoginUserKey(String userId) {
		
        return LOGIN_USER_INFO + ":" + userId;
    }
	
	public static String getRegistSessionVCodeKey(String sessionId,String phone) {
		
        return REGIST_SESSION_VCODE + ":" + sessionId + ":" + phone;
    }
}
