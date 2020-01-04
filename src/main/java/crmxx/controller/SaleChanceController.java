package crmxx.controller;

import base.BaseController;
import crmxx.model.ResultInfo;
import crmxx.po.SaleChance;
import crmxx.query.SaleChanceQuery;
import crmxx.service.SaleChanceService;
import crmxx.service.UserService;
import crmxx.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController{

    @Autowired
    private SaleChanceService saleChanceService;

    @Autowired
    private UserService userService;

    /**
     * 机会数据页面跳转
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "sale_chance";
    }

    /**
     * 机会数据查询（分页）
     * @param page
     * @param rows
     * @param saleChanceQuery
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> queryByParams(@RequestParam(defaultValue = "1")Integer page,
                                            @RequestParam(defaultValue = "10")Integer rows,
                                            SaleChanceQuery saleChanceQuery){
        saleChanceQuery.setPageNum(page);
        saleChanceQuery.setPageSize(rows);
        return saleChanceService.queryParamBydata(saleChanceQuery);
    }

    /**
     * 机会数据添加
     * @param request
     * @param saleChance
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest request,SaleChance saleChance){
//        获取用户ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
//        查询用户真实姓名并设置到查询参数中
        saleChance.setCreateMan(userService.queryById(userId).getTrueName());
//        执行机会数据的添加
        saleChanceService.saveSaleChance(saleChance);
        return success("机会数据添加成功！");
    }

    /**
     * 机会数据更新
     * @param saleChance
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SaleChance saleChance){
        saleChanceService.updateSaleChance(saleChance);
        return success("机会数据更新成功！");
    }

    /**
     * 机会数据删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        saleChanceService.deleteBatch(ids);
        return success("机会数据删除成功！");
    }

    @RequestMapping("/querySaleChanceBySid")
    public String querySaleChanceBySid(Integer sid, Model model){
        model.addAttribute("saleChance",saleChanceService.queryById(sid));
        return "sale_chance_dev";
    }

}
