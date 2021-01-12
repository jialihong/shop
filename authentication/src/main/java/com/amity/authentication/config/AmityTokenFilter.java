//package com.amity.authentication.config;
//
//import com.amity.authentication.login.TokenProvider;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 在spring security中复原“session”   获取token再次请求其他的接口，将token放在请求头中或者带上token=asdf这个参数
// * Created by Amity on 2021/1/7 11:58
// */
//public class AmityTokenFilter extends GenericFilterBean {
//
//    private final static Logger logger = LoggerFactory.getLogger(AmityTokenFilter.class);
//
//    private final static String TOKEN_NAME = "my-auth-token";
//
//    private UserDetailsService userDetailsService;
//
//    private TokenProvider tokenProvider;
//
//    public AmityTokenFilter(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
//        this.userDetailsService = userDetailsService;
//        this.tokenProvider = tokenProvider;
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
//        try {
//            HttpServletRequest request = (HttpServletRequest)servletRequest;
//            String authToken = request.getHeader(TOKEN_NAME);
//            if(StringUtils.hasText(authToken)) {
//                //从自定义tokenProvider中解析用户
//                String username = tokenProvider.getUsernameFromToken(authToken);
//                // 这里仍然是调用我们自定义的UserDetailsService，查库，检查用户名是否存在，
//                // 如果是伪造的token,可能DB中就找不到username这个人了，抛出异常，认证失败
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if(tokenProvider.validateToken(authToken, userDetails)) {
//                    logger.debug("validateToken ok...");
//                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//                    //存放认证信息，如果没有走这一步，下面的doFilter就会提示登录了
//                    SecurityContextHolder.getContext().setAuthentication(token);
//                }
//                //调用后续的Filter  如果上面的代码未能复原session，SecurityContext中没有信息，后面的流程会检测出"需要登录"
//                chain.doFilter(servletRequest, servletResponse);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
