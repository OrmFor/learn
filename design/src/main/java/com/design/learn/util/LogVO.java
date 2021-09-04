package com.design.learn.util;


import lombok.Data;

@Data
public class LogVO {
    //line 353: {"requestUrl":"/mmp-base/thirdParty/CustomerManagerTrack.do",
    // "msgId":"bcee7ef65f67056d_20210903163517017",
    // "requestTime":123,
    // "startTime":"2021-09-03 16:35:17.038",
    // "endTime":"2021-09-03 16:35:17.161",
    // "userId":"G12123"}

    private String requestUrl;

    private String msgId;

    private String  requestTime;

    private String startTime;

    private String endTime;

    private String userId;


}
