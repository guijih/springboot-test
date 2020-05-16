package com.example.springboot.handlerInterceptor;


import com.example.springboot.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtils redisUtils;


    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);


    /**
     * 在请求处理之前进行调用（Controller方法调用之前
     *
     * @param httpServletRequest
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        System.out.printf("preHandle被调用\n");
       /* response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;

            JSONObject res = new JSONObject();
            res.put("success","false");
            res.put("msg","xxxx");
            out = response.getWriter();
            out.append(res.toString());*/
//
        String test = redisUtils.get("test");
        System.out.println(test);
//
//        System.out.println();


        return true;


    }


    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用\n");
       /* String test = redisTemplate.opsForValue().get("test");
        logger.info(test);*/

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被调用\n");
    }

}