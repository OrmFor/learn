package spring.test.aop;

import com.design.learn.springdemo.aopdemo.TestBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class SpringAopTest {

    @Test
    public void test() throws IOException {
        //ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/application.xml");
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-aop-application.xml");
       // ClassPathResource cr = new ClassPathResource("spring-application.xml");
       // InputStream inputStream = cr.getInputStream();
        TestBean test = (TestBean) ac.getBean("test");
        test.test();
        //ac.getBean("carFactoryBean");
    }
}
