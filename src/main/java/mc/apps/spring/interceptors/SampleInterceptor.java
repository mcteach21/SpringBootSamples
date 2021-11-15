package mc.apps.spring.interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class SampleInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(SampleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle.. getRequestURL() : "+request.getRequestURL() +" | getRequestURI() : "+request.getRequestURI()+" | getMethod() : "+request.getMethod() + " | getQueryString() : " + request.getQueryString());

//        Map<String,String[]> params = getParameters(request);
//        //i18n
//        setLocale(params);

        return true; //(request.getRequestURI().equals("/"));
    }

//    private void setLocale(Map<String, String[]> params) {
//        String lang = "fr";
//
//        if (params.keySet().contains("lang")){
//            Optional<String> lang_first = Arrays.stream(params.get("lang")).findFirst();
//            if (!lang_first.isEmpty())
//               lang = lang_first.get();
//        }
//
//        //set locale..
//
//    }
//
//    private Map<String, String[]> getParameters(HttpServletRequest request) {
//        Map<String, String[]> params = request.getParameterMap();
//        for (String key : params.keySet()) {
//            String[] strArr = params.get(key);
//            for (String val : strArr)
//                logger.info(key + " = " + val);
//        }
//        return params;
//    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle..");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        logger.info("afterCompletion..");
    }
}
