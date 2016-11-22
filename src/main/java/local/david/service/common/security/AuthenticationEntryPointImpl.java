package local.david.service.common.security;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by [david] on 22.11.16.
 */
@Service("authenticationEntryPoint")
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authenticationException)
            throws IOException, ServletException {
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
        // authenticationException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        Error error = new Error(authenticationException.getMessage() == null
                ? "Invalid username or password"
                : authenticationException.getMessage());
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, error);
        out.close();
    }

}