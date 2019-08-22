/**
 * Jdk动态代理只能代理到调用的那个接口方法，不能代理到里面嵌套的方法
 * spring aop动态代理的坑，嵌套事物会出问题（在一个写方法里面for循环写入时，
 * 假设int i = 5时报错，需要回滚所有数据时，就需要把 proxy-targe-class 打开）
 *
 *
 * @Transactional 注解只能应用到 public 可见度的方法上 。
 * 如果你在 protected、private 或者 package-visible
 * 的方法上使用 @Transactional 注解，它也不会报错，
 * 但是这个被注解的方法将不会展示已配置的事务设置。
 *
 *
 * 因为spring事物是基于类和接口的所以只能在类里面调用另一个类里面的事物，同一个类里面调用自己类的事物方法是无效的
 *
 *     DefaultAopProxyFactory.java
 *     public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
 *           // proxy_target_class = true 时会 走else  然后会创建 CGLIB动态代理
 *         if (!config.isOptimize() && !config.isProxyTargetClass() && !this.hasNoUserSuppliedProxyInterfaces(config)) {
 *             return new JdkDynamicAopProxy(config);
 *         } else {
 *             Class<?> targetClass = config.getTargetClass();
 *             if (targetClass == null) {
 *                 throw new AopConfigException("TargetSource cannot determine target class: Either an interface or a target is required for proxy creation.");
 *             } else {
 *                 return (AopProxy)(!targetClass.isInterface() && !Proxy.isProxyClass(targetClass) ? new ObjenesisCglibAopProxy(config) : new JdkDynamicAopProxy(config));
 *             }
 *         }
 *     }
 *
 *
 *
 *
 *
 */
package com.design.learn.proxy.springproxy;