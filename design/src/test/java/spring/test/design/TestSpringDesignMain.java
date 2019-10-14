package spring.test.design;

import base.BaseTest;
import com.design.learn.springdemo.springdesign.warpper.CreateOrderWarpper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class TestSpringDesignMain extends BaseTest {

    @Autowired
    private CreateOrderWarpper createOrderWarpper;

    @Test
    public void testIOS() {
        if (createOrderWarpper.chcekIsOnLine())
            createOrderWarpper.preOrderCreate("IOS", new BigDecimal(200.0));

    }

    @Test
    public void testAndroid() {
        if (createOrderWarpper.chcekIsOnLine())
            createOrderWarpper.preOrderCreate("Android", new BigDecimal(200.0));

    }
}
