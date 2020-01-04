package crmxx.controller;

import base.BaseController;
import crmxx.model.ResultInfo;
import crmxx.po.CusDevPlan;
import crmxx.po.SaleChance;
import crmxx.query.CusDevPlanQuery;
import crmxx.query.SaleChanceQuery;
import crmxx.service.CusDevPlanService;
import crmxx.service.SaleChanceService;
import crmxx.service.UserService;
import crmxx.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private SaleChanceService saleChanceService;

    @Autowired
    private CusDevPlanService cusDevPlanService;

    @RequestMapping("index")
    public String index(){
        return "cus_dev_plan";
    }

    /**
     * 客户开发计划数据展示
     * @param page
     * @param rows
     * @param saleChanceQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryByParams(@RequestParam(defaultValue = "1" )Integer page,
                                            @RequestParam(defaultValue = "10") Integer rows,
                                            SaleChanceQuery saleChanceQuery, HttpServletRequest request){
        saleChanceQuery.setPageNum(page);
        saleChanceQuery.setPageSize(rows);
        saleChanceQuery.setState(1);
        saleChanceQuery.setAssignMan(userService.queryById(LoginUserUtil.releaseUserIdFromCookie(request)).getTrueName());
        return saleChanceService.queryParamBydata(saleChanceQuery);
    }

    /**
     * 根据营销机会id查询客户开发计划数据
     * @param page
     * @param rows
     * @param cusDevPlanQuery
     * @return
     */
    @RequestMapping("queryCusDevPlansBySid")
    @ResponseBody
    public Map<String,Object> queryCusDevPlanBySid(@RequestParam(defaultValue = "1" )Integer page,
                                                   @RequestParam(defaultValue = "10") Integer rows,
                                                   CusDevPlanQuery cusDevPlanQuery
                                                   ){
        cusDevPlanQuery.setPageNum(page);
        cusDevPlanQuery.setPageSize(rows);
        return cusDevPlanService.queryParamBydata(cusDevPlanQuery);
    }

    /**
     * 开发计划项数据保存
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success();
    }

    /**
     * 开发计划项数据更新
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success();
    }

    /**
     * 开发计划项数据删除
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id){
        cusDevPlanService.deleteCusDevPlan(id);
        return success();
    }
}
