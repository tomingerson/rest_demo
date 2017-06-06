package demo;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Created by ergouser on 30.05.17.
 */
@Component
public class HeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String funny = request.getHeader("funny");
        if (funny != null && funny.equals("mysecrete")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Secret is not correct");
        } else {
            response.setHeader("funny", "myothersecret");
            filterChain.doFilter(request, response);
        }
    }
}
