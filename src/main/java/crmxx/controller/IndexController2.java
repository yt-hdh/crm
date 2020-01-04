package crmxx.controller;

import base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("index2")
public class IndexController2 extends BaseController {

    public String index2(){
        return "index2";
    }

}
