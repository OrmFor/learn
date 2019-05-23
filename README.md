# learn
### 代码结构
``` lua
com.de-sign.learn
├── annotation -- 自定义注解
├── basejava -- java基础
|    ├── InitDemo -- 类加载(new 关键字引出的数据加载)
|    ├── rbtree -- 红黑树
|    ├── regerance -- 四种引用(强引用，软引用，弱引用，虚引用)
├── ddos -- 模拟ddos攻击
├── jni -- jni调用
├── oom -- 模拟内存溢出(在resource下有配置文件，当发生oom时可以保存dump)
├── proxy -- 动态代理(JDKProxy 和 CGLIBProxy 以及Spring aop时没设置proxy-target-calss="true" 引发的坑)
├── sekill -- java秒杀(利用redis的watch功能)
├── springbean -- 模拟spring bean装载(BeanNameAware，BeanClassLoadAware，
|                 BeanFactoryAware，ApplicationContentAware,
|                 BenaPostProcessor中的postProcessBeforeInitialization,
|                 InitializationBean中的afterProperiesSet,
|                 init, BeanPostProcessor中的postProcessAfterInitalization)
├── springdesign -- 策略模式 + 适配器模式应用(利用Initialization的afterProperiesSet)
├── thread -- 线程相关
|    ├── blockqueue -- 阻塞队列的使用
|    ├── callable -- 利用线程池执行有返回值的线程(实现Callable)
|    ├── deathlock -- 死锁(4要素  1.互斥 2.请求和保持请求 3.不可剥夺 4.环路等待)
|    ├── future -- 线程futrue
├── tomcatdemo -- 手写一个超级简易的tomcat(Servlet,Request,Response)
└──  wx -- 简单的微信获取token(想用设计模式，没用好，作废烂尾，可以参考别人的优秀代码)

```
 

 ## java基础 
  ```
     com.design.learn.basejava   
  ```
 ## **java秒杀** 
  ```    
    com.design.learn.senkill
    描述：使用redis的watch功能实现秒杀
    测试：使用CyclicBarrier 注意和 CountDownLatch的区别
  ```    
 ## java ddos 
  ```
    com.design.learn.ddos
    描述：java模拟ddos攻击 
  ```
 ## java jni 
  ```
    com.design.learn.jni  
  ```
 ## java多线程  
  ``` 
    com.design.learn.thread   
    包括：阻塞队列、callable、死锁、future  
  ```
 ## **java tomcatdemo**  
  ``` lua 
    com.design.learn.tomcatdemo
  ```
  
 ## ~~java 微信接口~~   
 ``` lua  
    com.design.learn.wx  
    直接入了获取token,只是一个简单的小demo,没有人家开源的写的好，只是自己练练手而已
 ```
 
 ## java 动态代理
 ``` lua   
    com.design.learn.proxy   
    cglib和JDKProxy区别  
        cglib MethodInterceptor对实现该类的方法有效  
        JDKProxy实现的是InvocationHandler 并且只对接口有效 
    注意参数  里面还存在spring动态代理的坑 (事物嵌套问题) 
 ```
  
     
 ## **新增Spring bean生命周期相关**
 ``` lua
    com.design.learn.springbean
 ```

