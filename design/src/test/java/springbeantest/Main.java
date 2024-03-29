package springbeantest;

import base.BaseTest;
import com.design.learn.springdemo.factorybeandemo.SpringFactoryBeanDemo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class Main extends BaseTest {
  /*  @Autowired
    private MySpringBeanPostProcessor mySpringBeanPostProcessor;*/
   /* @Autowired
    private MySpringBean mySpringBean;*/

    @Test
    public void test() throws IOException {
        //ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/application.xml");
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-application.xml");
        ClassPathResource cr = new ClassPathResource("spring-application.xml");
        InputStream inputStream = cr.getInputStream();
         ac.getBean("mySpringBean");
        //ac.getBean("carFactoryBean");
    }
}
