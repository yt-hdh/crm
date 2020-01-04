package crmxx;

import base.constants.CrmContants;
import base.exceptions.LoginException;
import base.exceptions.ParamsException;
import com.alibaba.fastjson.JSON;
import crmxx.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理
 *1、Json异常
 * 2、视图异常
 * 3、登录异常
 */

@Component
public class GlobalExceptionResoler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv=getDefalutModelAndView(request);
//        判断是否是登陆异常
        if(ex instanceof LoginException){
            LoginException le= (LoginException) ex;
            mv.addObject("code",le.getCode());
            mv.addObject("msg",le.getMsg());
            return mv;
        }

        if(handler instanceof HandlerMethod){
            HandlerMethod hm= (HandlerMethod) handler;
            ResponseBody annotation = hm.getMethod().getAnnotation(ResponseBody.class);
            if(null==annotation){
//                视图异常处理
                if(ex instanceof ParamsException){
                    ParamsException px= (ParamsException) ex;
                    mv.addObject("code",px.getCode());
                    mv.addObject("msg",px.getMsg());
                }
                return mv;
            }else{
//                json异常处理
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setCode(CrmContants.FAILED_CODE);
                resultInfo.setMsg(CrmContants.FAILED_MSG);
                if(ex instanceof ParamsException){
                    ParamsException px= (ParamsException) ex;
                    resultInfo.setCode(px.getCode());
                    resultInfo.setMsg(px.getMsg());
                }

//                响应前端
                PrintWriter p=null;
                try{
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    p=response.getWriter();
                    p.write(JSON.toJSONString(resultInfo));
                    p.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }finally{
                    if(null!=p){
                        p.close();
                    }
                }
            }
            return null;
        }else {
            return mv;
        }
    }
//  设置默认异常
    private ModelAndView getDefalutModelAndView(HttpServletRequest request) {
        ModelAndView mv=new ModelAndView("error");
        mv.addObject("ctx",request.getContextPath());
        mv.addObject("msg", CrmContants.FAILED_MSG);
        mv.addObject("code", CrmContants.FAILED_CODE);
        mv.addObject("uri", request.getRequestURI());
        return mv;
    }
}
