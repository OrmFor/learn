package spring.test.springcyclic;

import base.BaseTest;
import com.design.learn.springdemo.springcyclic.TestA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations = {"classpath*:spring-application-cyclic.xml"})
public class Test extends BaseTest {

    @Autowired
    private TestA testA;

    @org.junit.Test
    public void test(){
        testA.getTestB();
    }
}
