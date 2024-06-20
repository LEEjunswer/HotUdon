package com.HotUdon.interceptor;

import com.HotUdon.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        String loginPath = request.getContextPath() + "/members/login";
        String[] protectedPaths = { "/boards/join", "/boards/delete", "/boards/update", "/members/profile","/members/delete","/members/update","/members/myPage","/members/updateForm","/reply/myReplyList/","/boards/myBoardList/" };
        boolean isProtectedPath = false;
        for (String path : protectedPaths) {
            if (uri.startsWith(request.getContextPath() + path)) {
                isProtectedPath = true;
                break;
            }
        }
        if (isProtectedPath) {
            if (session == null || session.getAttribute(SessionConst.USER_ID) == null) {
                if (!uri.equals(loginPath)) {
                    response.sendRedirect(loginPath);
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && request.getSession() != null) {
            HttpSession session = request.getSession();
            String loginId = (String) session.getAttribute(SessionConst.USER_ID);
            String nickname = (String) session.getAttribute(SessionConst.USER_NAME);
            String userRole =(String) session.getAttribute(SessionConst.USER_ROLE);
            modelAndView.addObject("loginId", loginId);
            modelAndView.addObject("nickname", nickname);
            modelAndView.addObject("userRole",userRole);
        }
    }

}
