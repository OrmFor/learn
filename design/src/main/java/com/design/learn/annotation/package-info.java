/**
 * @Author wwy
 * @Description <p>annotation 注解相关,自定义注解
 *        元注解的作用就是负责注解其他注解。Java5.0定义了4个标准的meta-annotation类型，
 *        它们被用来提供对其它 annotation类型作说明。Java5.0定义的元注解：
 * 　　　　1.@Target,
 *            @Target 说明了Annotation所修饰的对象范围：Annotation可被用于
 *            packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、
 *            方法参数和本地变量（如循环变量、catch参数）。
 *            在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
 * 　　　　2.@Retention,
 *            @Retention 定义了该Annotation被保留的时间长短：
 *            某些Annotation仅出现在源代码中，而被编译器丢弃；
 *            而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，
 *            而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
 *            使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
 * 　　　　3.@Documented,
 *            @Documented 可以被例如javadoc此类的工具文档化
 * 　　　　4.@Inherited
 *            @Inherited 阐述了某个被标注的类型是被继承的。
 *            如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 *
 *        在自定义注解时该4个元注解一般都会带上这些注解
 * </p>
 **/
package com.design.learn.annotation;