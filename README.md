# shiro
Simple permission management through shiro framework
使用springBoot 框架来讲 ssm 配置简化。同样是通过URL和权限一对一关联。

对于Shiro的配置采用SpringConfigure 注解类
声明Bean 函数来获取依赖注入的实例
对于自定义筛选器要额外注意，customFilter 由ShiroFilterFactoryBean 来构建实现，而通过作为Bean 注入时customFilter就会失效。



