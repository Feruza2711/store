package uz.pdp.store.security;//package org.shop.system.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.store.dto.UserDto;
import uz.pdp.store.redis.UserSession;
import uz.pdp.store.redis.UserSessionRepository;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                if (jwtUtil.checkExpiration(token)) {
                    String id = (String) jwtUtil.getClaim(token, "sub");
                    Optional<UserSession> optional = userSessionRepository.findById(Integer.valueOf(id));

                    optional.ifPresent(session -> {
                        UserDto userDto = session.getUserDto();
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDto,
                                        null,
                                        userDto.getAuthorities()
                                );

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });

                }
            }
        }catch (Exception e){
            log.error("Error while checking token in JWT filter: ".concat(e.getMessage()));
        }
        filterChain.doFilter(request, response);
    }



}
