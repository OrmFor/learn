//package com.design.learn.wx.mapping;
//
//import com.design.learn.wx.bean.base.WxpayRequest;
//import com.design.learn.wx.bean.base.WxpayResponse;
//import com.design.learn.wx.exception.WxpayApiException;
//import org.apache.poi.poifs.crypt.Decryptor;
//
//public interface Converter {
//
//        /**
//         * 把字符串转换为响应对象。
//         *
//         * @param <T> 领域泛型
//         * @param rsp 响应字符串
//         * @param clazz 领域类型
//         * @return 响应对象
//         * @throws WxpayApiException
//         */
//        public <T extends WxpayResponse> T toResponse(String rsp, Class<T> clazz)
//                throws WxpayApiException;
//
//        /**
//         * 获取响应内的签名数据
//         *
//         * @param request
//         * @param responseBody
//         * @return
//         * @throws WxpayApiException
//         */
//        public SignItem getSignItem(WxpayRequest<?> request, String responseBody)
//                throws WxpayApiException;
//
//        /**
//         *  获取解密后的响应内的真实内容
//         *
//         * @param request
//         * @param body
//         * @param format
//         * @param decryptor
//         * @param encryptType
//         * @param charset
//         * @return
//         * @throws WxpayApiException
//         */
//        public String decryptSourceData(WxpayRequest<?> request, String body, String format,
//                                        Decryptor decryptor, String encryptType, String charset)
//                throws WxpayApiException;
//
//    }
