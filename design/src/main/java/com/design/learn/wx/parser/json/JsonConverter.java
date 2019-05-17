//package com.design.learn.wx.parser.json;
//
//package com.Wxpay.api.internal.parser.json;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//
//import com.design.learn.wx.bean.base.WxpayRequest;
//import com.design.learn.wx.bean.base.WxpayResponse;
//import com.design.learn.wx.exception.WxpayApiException;
//import com.design.learn.wx.mapping.Converter;
//import org.apache.poi.poifs.crypt.Decryptor;
//
///**
// * JSON格式转换器。
// *
// * @author carver.gu
// * @since 1.0, Apr 11, 2010
// */
//public class JsonConverter implements Converter {
//
//    public <T extends WxpayResponse> T toResponse(String rsp, Class<T> clazz)
//            throws WxpayApiException {
//        JSONReader reader = new JSONVWxdatingReader(new ExceptionErrorListener());
//        Object rootObj = reader.read(rsp);
//        if (rootObj instanceof Map<?, ?>) {
//            Map<?, ?> rootJson = (Map<?, ?>) rootObj;
//            Collection<?> values = rootJson.values();
//            for (Object rspObj : values) {
//                if (rspObj instanceof Map<?, ?>) {
//                    Map<?, ?> rspJson = (Map<?, ?>) rspObj;
//                    return fromJson(rspJson, clazz);
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 把JSON格式的数据转换为对象。
//     *
//     * @param <T> 泛型领域对象
//     * @param json JSON格式的数据
//     * @param clazz 泛型领域类型
//     * @return 领域对象
//     * @throws WxpayApiException
//     */
//    public <T> T fromJson(final Map<?, ?> json, Class<T> clazz) throws WxpayApiException {
//        return Converters.convert(clazz, new Reader() {
//            public boolean hasReturnField(Object name) {
//                return json.containsKey(name);
//            }
//
//            public Object getPrimitiveObject(Object name) {
//                return json.get(name);
//            }
//
//            public Object getObject(Object name, Class<?> type) throws WxpayApiException {
//                Object tmp = json.get(name);
//                if (tmp instanceof Map<?, ?>) {
//                    Map<?, ?> map = (Map<?, ?>) tmp;
//                    return fromJson(map, type);
//                } else {
//                    return null;
//                }
//            }
//
//            public List<?> getListObjects(Object listName, Object itemName, Class<?> subType)
//                    throws WxpayApiException {
//                List<Object> listObjs = null;
//
//                Object listTmp = json.get(listName);
//                if (listTmp instanceof Map<?, ?>) {
//                    Map<?, ?> jsonMap = (Map<?, ?>) listTmp;
//                    Object itemTmp = jsonMap.get(itemName);
//                    if (itemTmp == null && listName != null) {
//                        String listNameStr = listName.toString();
//                        itemTmp = jsonMap.get(listNameStr.substring(0, listNameStr.length() - 1));
//                    }
//                    if (itemTmp instanceof List<?>) {
//                        listObjs = getListObjectsInner(subType, itemTmp);
//                    }
//                } else if (listTmp instanceof List<?>) {
//                    listObjs = getListObjectsInner(subType, listTmp);
//                }
//
//                return listObjs;
//            }
//
//            private List<Object> getListObjectsInner(Class<?> subType, Object itemTmp)
//                    throws WxpayApiException {
//                List<Object> listObjs;
//                listObjs = new ArrayList<Object>();
//                List<?> tmpList = (List<?>) itemTmp;
//                for (Object subTmp : tmpList) {
//                    Object obj = null;
//                    if (String.class.isAssignableFrom(subType)) {
//                        obj = subTmp;
//                    } else if (Long.class.isAssignableFrom(subType)) {
//                        obj = subTmp;
//                    } else if (Integer.class.isAssignableFrom(subType)) {
//                        obj = subTmp;
//                    } else if (Boolean.class.isAssignableFrom(subType)) {
//                        obj = subTmp;
//                    } else if (Date.class.isAssignableFrom(subType)) {
//                        DateFormat format = new SimpleDateFormat(WxpayConstants.DATE_TIME_FORMAT);
//                        try {
//                            obj = format.parse(String.valueOf(subTmp));
//                        } catch (ParseException e) {
//                            throw new WxpayApiException(e);
//                        }
//                    } else if (subTmp instanceof Map<?, ?>) {// object
//                        Map<?, ?> subMap = (Map<?, ?>) subTmp;
//                        obj = fromJson(subMap, subType);
//                    }
//
//                    if (obj != null) {
//                        listObjs.add(obj);
//                    }
//                }
//                return listObjs;
//            }
//
//        });
//    }
//
//    /**
//     * @see com.Wxpay.api.internal.mapping.Converter#getSignItem(com.Wxpay.api.WxpayRequest, String)
//     */
//    public SignItem getSignItem(WxpayRequest<?> request, String responseBody)
//            throws WxpayApiException {
//
//        // 响应为空则直接返回
//        if (StringUtils.isEmpty(responseBody)) {
//
//            return null;
//        }
//
//        SignItem signItem = new SignItem();
//
//        // 获取签名
//        String sign = getSign(responseBody);
//        signItem.setSign(sign);
//
//        // 签名源串
//        String signSourceData = getSignSourceData(request, responseBody);
//        signItem.setSignSourceDate(signSourceData);
//
//        return signItem;
//    }
//
//    /**
//     *
//     * @param request
//     * @param body
//     * @return
//     */
//    private String getSignSourceData(WxpayRequest<?> request, String body) {
//
//        // 加签源串起点
//        String rootNode = request.getApiMethodName().replace('.', '_')
//                + WxpayConstants.RESPONSE_SUFFIX;
//        String errorRootNode = WxpayConstants.ERROR_RESPONSE;
//
//        int indexOfRootNode = body.indexOf(rootNode);
//        int indexOfErrorRoot = body.indexOf(errorRootNode);
//
//        // 成功或者新版接口
//        if (indexOfRootNode > 0) {
//
//            return parseSignSourceData(body, rootNode, indexOfRootNode);
//
//            // 老版本失败接口
//        } else if (indexOfErrorRoot > 0) {
//
//            return parseSignSourceData(body, errorRootNode, indexOfErrorRoot);
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     *   获取签名源串内容
//     *
//     *
//     * @param body
//     * @param rootNode
//     * @param indexOfRootNode
//     * @return
//     */
//    private String parseSignSourceData(String body, String rootNode, int indexOfRootNode) {
//
//        //  第一个字母+长度+引号和分号
//        int signDataStartIndex = indexOfRootNode + rootNode.length() + 2;
//        int indexOfSign = body.indexOf("\"" + WxpayConstants.SIGN + "\"");
//
//        if (indexOfSign < 0) {
//
//            return null;
//        }
//
//        // 签名前-逗号
//        int signDataEndIndex = indexOfSign - 1;
//
//        return body.substring(signDataStartIndex, signDataEndIndex);
//    }
//
//    /**
//     * 获取签名
//     *
//     * @param body
//     * @return
//     */
//    private String getSign(String body) {
//
//        JSONReader reader = new JSONVWxdatingReader(new ExceptionErrorListener());
//        Object rootObj = reader.read(body);
//        Map<?, ?> rootJson = (Map<?, ?>) rootObj;
//
//        return (String) rootJson.get(WxpayConstants.SIGN);
//    }
//
//
//    public String decryptSourceData(WxpayRequest<?> request, String body, String format,
//                                    Decryptor decryptor, String encryptType, String charset)
//            throws WxpayApiException {
//
//        ResponseParseItem respSignSourceData = getJSONSignSourceData(request, body);
//
//        String bodyIndexContent = body.substring(0, respSignSourceData.getStartIndex());
//        String bodyEndContent = body.substring(respSignSourceData.getEndIndex());
//
//        return bodyIndexContent
//                + decryptor.decrypt(respSignSourceData.getEncryptContent(), encryptType, charset)
//                + bodyEndContent;
//
//    }
//
//    /**
//     *  获取JSON响应加签内容串
//     *
//     * @param request
//     * @param body
//     * @return
//     */
//    private ResponseParseItem getJSONSignSourceData(WxpayRequest<?> request, String body) {
//
//        String rootNode = request.getApiMethodName().replace('.', '_')
//                + WxpayConstants.RESPONSE_SUFFIX;
//        String errorRootNode = WxpayConstants.ERROR_RESPONSE;
//
//        int indexOfRootNode = body.indexOf(rootNode);
//        int indexOfErrorRoot = body.indexOf(errorRootNode);
//
//        if (indexOfRootNode > 0) {
//
//            return parseJSONSignSourceData(body, rootNode, indexOfRootNode);
//
//        } else if (indexOfErrorRoot > 0) {
//
//            return parseJSONSignSourceData(body, errorRootNode, indexOfErrorRoot);
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     *
//     *
//     * @param body
//     * @param rootNode
//     * @param indexOfRootNode
//     * @return
//     */
//    private ResponseParseItem parseJSONSignSourceData(String body, String rootNode,
//                                                      int indexOfRootNode) {
//
//        int signDataStartIndex = indexOfRootNode + rootNode.length() + 2;
//        int indexOfSign = body.indexOf("\"" + WxpayConstants.SIGN + "\"");
//
//        if (indexOfSign < 0) {
//
//            indexOfSign = body.length();
//        }
//
//        int signDataEndIndex = indexOfSign - 1;
//
//        String encryptContent = body.substring(signDataStartIndex + 1, signDataEndIndex - 1);
//
//        return new ResponseParseItem(signDataStartIndex, signDataEndIndex, encryptContent);
//    }
//
//}
