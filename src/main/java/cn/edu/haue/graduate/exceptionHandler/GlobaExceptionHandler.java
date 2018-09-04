package cn.edu.haue.graduate.exceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by dlj on 2018/9/4 15:36
 */
@ControllerAdvice
public class GlobaExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Exception e)  {
        StringBuffer requestURL = request.getRequestURL();
        String message = e.getMessage();
        ModelAndView mv =new ModelAndView();
        mv.addObject("exception",e);
        mv.addObject("message",message);
        mv.setViewName("admin/exception");
        //打印异常信息：
        e.printStackTrace();
        return mv;

    }

}
