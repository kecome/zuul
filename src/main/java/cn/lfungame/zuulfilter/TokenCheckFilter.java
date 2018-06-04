package cn.lfungame.zuulfilter;

import cn.lfungame.exception.BusinessException;
import cn.lfungame.exception.ErrorInfo;
import cn.lfungame.service.TokenService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORWARD_TO_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 15:38
 * @Description: 后端接口访问token验证
 */
public class TokenCheckFilter extends ZuulFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return !ctx.containsKey(FORWARD_TO_KEY) // a filter has already forwarded
                && !ctx.containsKey(SERVICE_ID_KEY); // a filter has already determined serviceId
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)) {
            Long id = tokenService.getToken(token);
            if(!StringUtils.isEmpty(id)){
                ctx.addZuulRequestHeader("id", tokenService.getToken(request.getHeader("token")) + "");
                return null;
            }
        }
        ctx.setSendZuulResponse(false);
        String msgBody = "{\n" +
                "    \"code\": 703,\n" +
                "    \"message\": \"您尚未登陆，或者Session超时\",\n" +
                "    \"data\": null\n" +
                "}";
        ctx.setResponseStatusCode(401);
        ctx.setResponseBody(msgBody);
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        if (request.getParameter("sample") != null) {
            // put the serviceId in `RequestContext`
            ctx.put(SERVICE_ID_KEY, request.getParameter("foo"));

        }
        return null;
    }
}
