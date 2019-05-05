package com.design.learn.tomcatdemo;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

public class MyRequest {

    private String url;
    private String method;

    public MyRequest(InputStream stream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestByte = new byte[1024];

        int length = 0;

        if ((length = stream.read(httpRequestByte)) > 0) {
            httpRequest = new String(httpRequestByte, 0, length);
        }

        //请求头
        // Accept:
        // text / javascript, application / javascript, application / ecmascript, application / x - ecmascript, */*; q=0.01
        //Accept-Encoding: gzip, deflate, br
        //Accept-Language: zh-CN,zh;q=0.9
        // Connection: keep-alive
        //Cookie: _ga=GA1.2.1677983785.1527130045; remember_token=d74dda34-df39-421a-b587-def000b70b03; remember_team_guid=fc4d22e66d3e4bd5a0e305ff4c5977df; _tower2_session=cfb8db715564198712fec897ba00f8bf; Hm_lvt_e1fa918d304786452f7d6936febd93c6=1556069062,1556249224,1556437871,1557027426; Hm_lpvt_e1fa918d304786452f7d6936febd93c6=1557027426; _gid=GA1.2.901896386.1557027426
        //Host: tower.im
        //Referer: https://tower.im/projects/b45a29ba10794a57b41e8bec37d3586c/docs/d88c565d47371d160d92b274712446da/
        //User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Mobile Safari/537.36
        //X-CSRF-Token: TR3vMmqvJPdI7Zp/Lh6gZOIQybYhyxV8vEmVPMbe0EG949QCSBuTsh5r2cwDUCu3Di82Xk/t9EB+mJINhtIy0A==
        //X-Requested-With: XMLHttpRequest
        String httpHead = httpRequest.split("\n")[0];

        //请求头为默认请求头不能自己设定，否则会报错
        url = httpHead.split("\\s")[1];

        method = httpHead.split("\\s")[0];

        //System.out.print(MessageFormat.format("url={0},method={1},this={2}",url,method,this));

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
