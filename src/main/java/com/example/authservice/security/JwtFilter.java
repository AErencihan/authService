package com.example.authservice.security;


import com.example.authservice.util.TokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final TokenGenerator tokenGenerator;
    private final UserDetailsService userDetailsService;

    public JwtFilter(TokenGenerator tokenGenerator, UserDetailsService userDetailsService) {
        this.tokenGenerator = tokenGenerator;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);
        String username;

        try {
            if (!token.isBlank()){
                username = tokenGenerator.verifyJWT(token).getSubject();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);

        }catch (Exception e){ // burada direkt bir exception fırlatıldığında onu yansıtamıyoruz çünkü request daha tamamlanmamış oluyor o yüzden custom bir şeyler eklenmesi gerekiyor
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json");
            Map<String, String> errors = new HashMap<>();
            errors.put("error", e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            
            // HashMap'de ki objeji objectMapper ile Stringe çevirdik
           response.getWriter().write(objectMapper.writeValueAsString(errors));

        }
        //filterChain.doFilter(request, response);

    }


    private String getToken(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")){
            return "";
        }
        return header.substring(7);


    }
}





























