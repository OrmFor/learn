//package com.design.learn.wx.parser.json;
//
//import com.design.learn.wx.bean.base.WxpayRequest;
//import com.design.learn.wx.bean.base.WxpayResponse;
//import com.design.learn.wx.exception.WxpayApiException;
//import org.apache.poi.poifs.crypt.Decryptor;
//
///**
// * 响应解释器接口。响应格式可以是JSON, XML等等。
// *
// * @author carver.gu
// * @since 1.0, Apr 11, 2010
// */
//public interface WxpayParser<T extends WxpayResponse> {
//
//    /**
//     * 把响应字符串解释成相应的领域对象。
//     *
//     * @param rsp 响应字符串
//     * @return 领域对象
//     */
//    public T parse(String rsp) throws WxpayApiException;
//
//    /**
//     * 获取响应类类型。
//     */
//    public Class<T> getResponseClass() throws WxpayApiException;
//
//    /**
//     * 获取响应内的签名数据
//     *
//     * @param rsp 响应字符串
//     * @return
//     * @throws AlipayApiException
//     */
//        /*public SignItem getSignItem(WxpayRequest<?> request, String responseBody)
//                throws WxpayApiException;*/
//
//    /**
//     * 获取实际串：如果是加密内容则返回内容已经是解密后的实际内容了
//     *
//     * @param request
//     * @param body
//     * @param format
//     * @param decryptor
//     * @param encryptType
//     * @param charset
//     * @return
//     * @throws AlipayApiException
//     */
//    public String decryptSourceData(WxpayRequest<?> request, String body, String format,
//                                    Decryptor decryptor, String encryptType, String charset)
//            throws WxpayApiException;
//
//}
//
//}
