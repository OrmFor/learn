package com.design.learn.wx.parser.json;//package com.design.learn.wx.parser.json;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayParser;
//import com.alipay.api.AlipayRequest;
//import com.alipay.api.AlipayResponse;
//import com.alipay.api.Decryptor;
//import com.alipay.api.SignItem;
//import com.alipay.api.internal.mapping.Converter;
//import com.design.learn.wx.bean.base.WxpayResponse;
//import com.design.learn.wx.exception.WxpayApiException;
//import com.design.learn.wx.mapping.Converter;
//
///**
// * 单个JSON对象解释器。
// *
// * @author carver.gu
// * @since 1.0, Apr 11, 2010
// */
//public class ObjectJsonParser<T extends WxpayResponse> implements WxpayParser<T> {
//
//    private Class<T> clazz;
//
//    public ObjectJsonParser(Class<T> clazz) {
//        this.clazz = clazz;
//    }
//
//    public T parse(String rsp) throws WxpayApiException {
//        Converter converter = new JsonConverter();
//        return converter.toResponse(rsp, clazz);
//    }
//
//    public Class<T> getResponseClass() {
//        return clazz;
//    }
//
//    public SignItem getSignItem(AlipayRequest<?> request, String responseBody)
//            throws AlipayApiException {
//
//        Converter converter = new JsonConverter();
//
//        return converter.getSignItem(request, responseBody);
//    }
//
//    public String decryptSourceData(AlipayRequest<?> request, String body, String format,
//                                    Decryptor decryptor, String encryptType, String charset)
//            throws AlipayApiException {
//
//        Converter converter = new JsonConverter();
//
//        return converter.decryptSourceData(request, body, format, decryptor, encryptType, charset);
//    }
//
//}
