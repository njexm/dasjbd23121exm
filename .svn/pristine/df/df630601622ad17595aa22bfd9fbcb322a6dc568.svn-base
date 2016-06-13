package com.proem.exm.service.userManage;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proem.exm.dao.system.CtpRoleDao;
import com.proem.exm.dao.system.CtpUserDao;
import com.proem.exm.entity.system.CtpUser;
/**
 * 简单实现从数据库中读取用户进行登录认证
 * 
 * @author Caron
 *
 */
public class SecurityServiceImpl implements UserDetailsService, SecurityService{
	
	private static Logger logger = Logger.getLogger(SecurityServiceImpl.class);
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CtpUserDao  ctpUserDao;
	@Autowired
	private CtpRoleDao  ctpRoleDao;
	/**
	 * 登录方法调用
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
			long curTimeMillis = System.currentTimeMillis(); 
		
		if(username==null||username.length()<1)
		{ 
			throw new UsernameNotFoundException(""); 
		}
		
		logger.info("-----登录方法被调用，当前登录用户是：" + username + "; 当前毫秒数是：" + curTimeMillis + "-----");
		
		CtpUser  ctpUser= new CtpUser();
		ctpUser.setName(username);
		try {
			ctpUser = ctpUserDao.getCtpUserByName(username);
		}catch (Exception e) {
			throw new UsernameNotFoundException("登陆失败！"); 
		} 
		if (ctpUser==null) {
			throw new UsernameNotFoundException("用户"+username+"不存在!");
		}else{
			
			
			throw new UsernameNotFoundException("账号无效！只有正常状态用户才能登录！");
			}
	}
		
		

}
