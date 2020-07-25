package me.lv;

import com.alibaba.fastjson.JSONObject;
import me.lv.util.HttpUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzw
 * @date 2018/5/31
 */
@Aspect
@Configuration
public class LogRecord {
    private static final Logger logger = LoggerFactory.getLogger(LogRecord.class);

    @Pointcut("execution(* me.lv.controller.*Controller.*(..))")
    public void executeService() {
    }

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.info("\n>>>>>> {} 请求参数 >>>>>>\n请求IP:{} \nurl: {} \nmethod: {} \nuri: {} \nparams: {} \n请求返回值: {}\n>>>>>>>>>>>>\n",
                date, HttpUtils.getIpAddress(request), url, method, uri, queryString, JSONObject.toJSON(result));

        return result;
    }
}
