import base.BaseTest;
import com.design.learn.wx.bean.wxrequest.WxpayAccessTokenRequest;
import com.design.learn.wx.bean.wxrespone.WxpayAccessTokenResponse;
import com.design.learn.wx.warpper.DefaultWXClient;
import com.design.learn.wx.warpper.WXClient;
import org.junit.Test;

public class TestWxPay extends BaseTest {

    @Test
    public void test() {
        WXClient wxClient = null;
        try {
            wxClient = DefaultWXClient.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WxpayAccessTokenResponse response = (WxpayAccessTokenResponse) wxClient.getAccessToken(new WxpayAccessTokenRequest());
        System.out.println(response.getBody());
    }

    public static void main(String[] args){
        WXClient wxClient = null;
        try {
            wxClient = DefaultWXClient.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WxpayAccessTokenResponse response = (WxpayAccessTokenResponse) wxClient.getAccessToken(new WxpayAccessTokenRequest());
        System.out.println(response.getBody());
    }
}
