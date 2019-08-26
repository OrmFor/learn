package springbeantest;

import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends BaseTest {
  /*  @Autowired
    private MySpringBeanPostProcessor mySpringBeanPostProcessor;*/
   /* @Autowired
    private MySpringBean mySpringBean;*/

    @Test
    public void test(){
        //ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/application.xml");
        ApplicationContext ac= new ClassPathXmlApplicationContext("application.xml");
        ac.getBean("mySpringBean");
    }
}
