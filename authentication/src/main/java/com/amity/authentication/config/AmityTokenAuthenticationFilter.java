package com.amity.authentication.config;

import com.amity.authentication.common.StringConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Amity on 2021/1/12 15:26
 */
@Component
public class AmityTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AmityTokenAuthenticationFilter.class);

    private final String tokenHeader = "Authorization";
    private final String tokenStart = "Bearer ";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(tokenHeader);
        if(!StringUtils.isEmpty(header) && header.startsWith(tokenStart)) {
            //从request中获取token
            String token = header.substring(tokenStart.length());
            if(!StringUtils.isEmpty(token) && redisTemplate.hasKey(StringConstant.REDIS_KEY_PREFIX + token)) {
                //获取username
                String username = (String) redisTemplate.opsForValue().get(StringConstant.REDIS_KEY_PREFIX + token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //可以校验token和username是否有效，目前由于token对应username存在redis，都以默认都是有效的
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
