package base;

import crmxx.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @ModelAttribute
    public void preHandler(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());

    }

    public ResultInfo success(){

        return new ResultInfo();
    }

    public ResultInfo success(String msg){
        ResultInfo result=new ResultInfo();
        result.setMsg(msg);
        return result;
    }
    public ResultInfo success(Object obj){
        ResultInfo result=new ResultInfo();
        result.setResult(obj);
        return result;
    }

}
