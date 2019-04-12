/**
 * @Author wwy
 * @Description   适配器模式+策略模式，利用Spring初始化加载bean的时候InitializingBean借口 放入map中，
 *                然后利用key，value隐藏实现类
 *                在该模式下，如需要扩展，只需要在product包下继承service.RechargeWarpperAdapter适配器就行
 *
 *                备注：此功能需要集成springJunit  测试类为 TestSpringDesignMain
 **/
package com.design.learn.springdesign;
