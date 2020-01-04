package crmxx.service;

import base.BaseService;
import crmxx.po.CusDevPlan;
import crmxx.utils.AssertUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan,Integer> {
    /**
     * 开发计划项数据添加
     * @param cusDevPlan
     */
    public void saveCusDevPlan(CusDevPlan cusDevPlan){
//        1、参数合法性校验
//        2、执行添加
        checkParams(cusDevPlan);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());
        cusDevPlan.setIsValid(1);
        AssertUtils.isTrue(save(cusDevPlan)==0,"执行添加失败！");
    }

    /**
     * 开发计划项数据更新
     * @param cusDevPlan
     */
    public void updateCusDevPlan(CusDevPlan cusDevPlan){
//        1、参数合法性校验
//        2、执行添加
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());
        cusDevPlan.setIsValid(1);
        AssertUtils.isTrue(null==queryById(cusDevPlan.getId()),"待更新数据不存在！");
        AssertUtils.isTrue(update(cusDevPlan)==0,"执行添加失败！");
    }

    /**
     * 开发计划项数据删除
     * @param id
     */
    public void deleteCusDevPlan(Integer id){
        //查询待删除的数据是否存在
        CusDevPlan cusDevPlan=queryById(id);
        AssertUtils.isTrue(null==cusDevPlan,"待删除数据不存在！");
        //设置数据失效
        cusDevPlan.setIsValid(0);
        AssertUtils.isTrue(update(cusDevPlan)==0,"执行删除失败！");
    }

    /**
     * 传入参数校验
     * @param cusDevPlan
     */
    private void checkParams(CusDevPlan cusDevPlan) {
        AssertUtils.isTrue(cusDevPlan.getPlanItem().equals(""),"开发计划项数据内容不能为空！");
    }

}
