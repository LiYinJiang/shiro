package eryingzhang.net.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eryingzhang.net.filter.URLPathMatchingFilter;
import eryingzhang.net.realm.DatabaseRealm;

@Configuration
public class ShiroConfiguration {

	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 
	 * ShiroFilterFactoryBean 处理拦截资源文件问题 在初始化过程中余姚注入SecurityManager Filter Chain 说明
	 * <p>
	 * 1、一个URL可以被多个Filter过滤，使用逗号分隔过滤器 2、设置多个Filter后，全部验证通过才能进行访问 3、部分过滤器可指定参数,如
	 * perms,roles
	 * </p>
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 初始化必选项
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 不设置会默认去web工程根目录下寻找"/login.jsp"
		shiroFilterFactoryBean.setLoginUrl("/login");

		// 登录成功后跳转页面
		shiroFilterFactoryBean.setSuccessUrl("/index");

		// 验证未通过跳转的未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

		// filterMap
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

		Map<String, Filter> customisedFilter = new LinkedHashMap<>();
		customisedFilter.put("url", getURLPathMatchingFilter());

		// 配置映射关系
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/config/**", "url");
		filterChainDefinitionMap.put("/doLogout", "logout");
		filterChainDefinitionMap.put("/**", "url");
		shiroFilterFactoryBean.setFilters(customisedFilter);

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 不能加入@Bean 原因是 shiro 中实例化 filter的 ShiroFilterFactoryBean 也作为一个Bean存在，
	 * 当他们都作为Bean 被实例化时，自定义的filter中规则就不会生效。
	 */
	public URLPathMatchingFilter getURLPathMatchingFilter() {
		return new URLPathMatchingFilter();
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		securityManager.setRealm(getDatabaseRealm());
		return securityManager;
	}

	@Bean
	public DatabaseRealm getDatabaseRealm() {
		DatabaseRealm realm = new DatabaseRealm();
		realm.setCredentialsMatcher(hashedCredentialsMatcher());

		return realm;
	}

	/**
	 * 凭证匹配器
	 * 
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");

		matcher.setHashIterations(2);// 散列次数， 相当于md5(md5(""));

		return matcher;
	}

	/**
	 * 开启shiro aop 注解支持 使用代理方式就需要开启代码支持
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
