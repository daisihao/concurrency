package com.daisihao.concurrency.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpRequest request = (HttpRequest) servletRequest;
        log.info("拦截到了servlet请求,当前的线程ID是：{}",Thread.currentThread().getId());
        //获取到当前的线程ID,放入ThreadLocal对象中
        RequestHolder.add(Thread.currentThread().getId());
        //拦截完请求后继续做后面的事情
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
