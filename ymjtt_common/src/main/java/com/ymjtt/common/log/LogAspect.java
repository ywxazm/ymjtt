package com.ymjtt.common.log;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/10 15:31
 **/
@Aspect //该注解标示该类为切面类
@Component
public class LogAspect {

    private static Logger logger = null;

    private static Map<String, Long> startTimeMap = new HashMap<>();

    @Before("execution(* com.ymjtt..*.*(..))")
    public void beforeLog(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().toString();
        List<Object> paramList = Arrays.asList(joinPoint.getArgs());
        Long threadId = Thread.currentThread().getId();
        logger = LoggerFactory.getLogger(className);
        if (className.contains("web")) {
            startTimeMap.put("web" + threadId, System.currentTimeMillis());
            String ip = getIp2(getRequest(joinPoint));
            logger.debug("Ymjtt_Log-->: Control ip = {}, threadId = {}, className = {}, params = {}", ip, threadId, className , paramList);
        }else if(className.contains("service")) {
            startTimeMap.put("service" + threadId, System.currentTimeMillis());
            logger.debug("Ymjtt_Log-->: Service threadId = {}, className = {}, params = {}", threadId, className, paramList);
        }else {
            startTimeMap.put("others" + threadId, System.currentTimeMillis());
            logger.debug("Ymjtt_Log-->: Others threadId = {}, className = {}, params = {}", threadId, className, paramList);
        }
    }

    @AfterReturning("execution(* com.ymjtt..*.*(..))")
    public void afterLog(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().toString();
        Long threadId = Thread.currentThread().getId();
        logger = LoggerFactory.getLogger(className);
        if (className.contains("web")) {
            String ip = getIp2(getRequest(joinPoint));
            Long castTime = System.currentTimeMillis() - startTimeMap.get("web" + threadId);
            logger.debug("Ymjtt_Log-->: Control ip = {}, threadId = {}, className = {}, castTime = {}ms", ip, threadId, className, castTime);
        } else if (className.contains("service")) {
            Long castTime = System.currentTimeMillis() - startTimeMap.get("service" + threadId);
            logger.debug("Ymjtt_Log-->: Service threadId = {}, className = {}, castTime = {}ms", threadId, className, castTime);
        } else {
            Long castTime = System.currentTimeMillis() - startTimeMap.get("others" + threadId);
            logger.debug("Ymjtt_Log-->: Others threadId = {}, className = {}, castTime = {}ms", threadId, className, castTime);
        }
    }

    /**
     * 获取request
     * @author  ywx
     * @date    2019/1/10 16:22
     * @param   [point]
     * @return  javax.servlet.http.HttpServletRequest
     */
    private HttpServletRequest getRequest(JoinPoint point) {
        Object[] args = point.getArgs();
        for (Object obj : args) {
            if (obj instanceof HttpServletRequest)
                return (HttpServletRequest) obj;
        }
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra.getRequest();
    }

    /**
     * 获取IP
     * @author  ywx
     * @date    2019/1/10 16:22
     * @param   [request]
     * @return  java.lang.String
     */
    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
