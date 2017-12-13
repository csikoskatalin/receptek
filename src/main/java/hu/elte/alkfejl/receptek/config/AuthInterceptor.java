package hu.elte.alkfejl.receptek.config;
import hu.elte.alkfejl.receptek.model.User;
import hu.elte.alkfejl.receptek.service.UserService;
import hu.elte.alkfejl.receptek.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        List<User.Role> routeRoles = getRoles((HandlerMethod) handler);
        User user = userService.getUser();

        if (routeRoles.isEmpty() || routeRoles.contains(User.Role.GUEST)) {
            return true;
        }

        if (userService.isLoggedIn() && routeRoles.contains(user.getRole())) {
            return true;
        }
        response.setStatus(401);
        return false;
    }

    private List<User.Role> getRoles(HandlerMethod handler) {
        Role role = handler.getMethodAnnotation(Role.class);
        return role == null ? Collections.emptyList() : Arrays.asList(role.value());
    }
}
