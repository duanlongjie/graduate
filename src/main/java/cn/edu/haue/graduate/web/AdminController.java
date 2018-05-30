package cn.edu.haue.graduate.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 后台控制器
 * Created by 杨晋升 on 2018/5/30.
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //登陆页面
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/admin/login");
        AuthenticationException exception = (AuthenticationException) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (exception != null) {
            modelAndView.addObject("error_msg", exception.getMessage());
        }
        return modelAndView;
    }

    //首页
    @RequestMapping(value = "/home")
    @PreAuthorize("hasAnyRole()")
    public @ResponseBody
    String home() {
        logger.info("admin登陆");
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
