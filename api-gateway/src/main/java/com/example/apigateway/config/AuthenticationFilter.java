
package com.example.apigateway.config;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 1;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        String id = null;

        RequestContext ctx = RequestContext.getCurrentContext();
        String un = SecurityContextHolder.getContext().getAuthentication().getName();

        if (ctx.getRequest().getHeader("Authorization") != null) {
            id = jwtUtil.getUserIdFromToken(ctx.getRequest().getHeader("Authorization").substring(7));
        }

        ctx.addZuulRequestHeader("username", un);
        ctx.addZuulRequestHeader("userId", id);
        return null;
    }
}
