package com.app.jungsuri.common.interceptor;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountInterceptor implements HandlerInterceptor {

    private final AccountRepository accountRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (modelAndView != null && authentication.getPrincipal() != "anonymousUser" && !isRedirectView(modelAndView)) {
            String loginId = authentication.getName();
            AccountEntity accountEntity = accountRepository.findByLoginId(loginId).orElseThrow(()-> new IllegalArgumentException("해당하는 아이디가 없습니다.1"));
            modelAndView.addObject("userRole", accountEntity.getUserRole().toString());
        }
    }

    private boolean isRedirectView(ModelAndView modelAndView) {
        return modelAndView.getViewName().startsWith("redirect") || modelAndView.getView() instanceof RedirectView;
    }
}
