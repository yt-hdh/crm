package crmxx.controller;

import base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("index2")
public class IndexController2 extends BaseController {

    @RequestMapping("index2")
    public String index2(){
        return "index2";
    }
    @RequestMapping("list")
    @ResponseBody
    public List<String> getList(){
        List<String> list=new ArrayList<>();
        list.add("wosi");
        list.add("nicai");
        list.add("wbd");
        return list;
    }


}
