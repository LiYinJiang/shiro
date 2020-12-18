# shiro
Simple permission management through shiro framework
将权限通过URL 关联，
数据库权限表中建立权限和URL一对一关系 
好处：不需要从Controller中使用注解来什么权限。
通过过滤器对访问的URI判断，业务完成后，权限变更和添加只需要修改数据库即可。
