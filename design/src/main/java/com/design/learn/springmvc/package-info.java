/**
* @Author wwy
* @Description
 *
 *
 * springMVC流程
 *
 *     request  --->  DispatherServlet.java 中的 doDispath()
 *
 *     mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
 *
 *     RequestMappingHandlerAdapter. handleInternal()  --> invokeHandlerMethod()
 *
 *     --->ServletInvocableHandlerMethod .invokeAndHandle() ---> 利用反射调用 invokeForRequest()
 *
 *
 *
 *
**/

package com.design.learn.springmvc;