package crmxx.controller;

import base.BaseController;
import crmxx.dto.TreeDto;
import crmxx.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("index")
    public String index(){
        return "module";
    }

    @RequestMapping("queryModules")
    @ResponseBody
    public List<TreeDto> queryModules(){
        return moduleService.queryModules();
    }
}
