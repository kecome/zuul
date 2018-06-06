package cn.lfungame.interceptor;

import cn.lfungame.exception.BusinessException;
import cn.lfungame.exception.ErrorInfo;
import cn.lfungame.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xuke
 * @Date: 2018/6/1 16:53
 * @Description:
 */
public class LoginInterceptor implements HandlerInterceptor {

    private TokenService tokenService;

    public LoginInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("测试代码。。。。。");
        if (!HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class clazz = handlerMethod.getBeanType();
        if(!clazz.getPackage().getName().startsWith("cn.lfungame")) {
            return true;
        }
        //判断请求处理类是否加了LoginIgnore注解
        Annotation clazzAnnotation = clazz.getAnnotation(LoginIgnore.class);
        //判断请求方法是否加了LoginIgnore注解  只有返回true才会继续向下执行，返回false取消当前请求
        Annotation methodAnnotation = handlerMethod.getMethod().getAnnotation(LoginIgnore.class);
        if(clazzAnnotation == null && methodAnnotation == null) {  //没加忽略登录拦截注解，进入token验证
            String token = request.getHeader("token");
            if(token == null || token.equals("")) {
                throw new BusinessException(ErrorInfo.USER_IS_NULL.code, ErrorInfo.USER_IS_NULL.desc);
            }
            Long id = tokenService.getToken(token);
            if(id == null) {
                throw new BusinessException(ErrorInfo.USER_IS_NULL.code, ErrorInfo.USER_IS_NULL.desc);
            }
            tokenService.updateExpire(id+token, 1, TimeUnit.DAYS);
            return true;
        }else {  //忽略登录拦截
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
