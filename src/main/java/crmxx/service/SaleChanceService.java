package crmxx.service;

import base.BaseService;
import crmxx.db.dao.SaleChanceMapper;
import crmxx.enums.DevResultStatus;
import crmxx.enums.StateStatus;
import crmxx.po.SaleChance;
import crmxx.utils.AssertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.regex.Pattern;

@Service
public class SaleChanceService extends BaseService<SaleChance,Integer> {
//    手机号正则校验

    private Pattern p  = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");

    /**
     * 创建营销机会数据
     * @param saleChance
     */
    public void saveSaleChance(SaleChance saleChance){
        /**
         * 1、参数的合法性校验
         *      {
         *          客户名称 非空
         *          联系人   非空
         *          联系电话  非空   长度11位   合法手机号码
         *          成功几率  >0
         *      }
         *  2、分配状态
         *      {
         *      设置枚举类
         *          已选择分配人   state=1   已分配   设置分配时间
         *          未选择分配人   state=0    未分配
         *      }
         *  3、开发状态
         *      {
         *          设置枚举类    0表示未开发，1表示开发中，2表示开发成功，3表示开发失败
         *      }
         *  4、有效值    默认有效    创建时间&更新时间为    当前时间
         *  5、创建用户    默认是系统登录用户
         */
        checkSaleChanceParams(saleChance);
        saleChance.setState(StateStatus.UN_STATED.getState());
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        saleChance.setDevResult(DevResultStatus.UN_DEV.getDevstatus());
        if(StringUtils.isNoneBlank(saleChance.getAssignMan())){
            saleChance.setAssignTime(new Date());
            saleChance.setState(StateStatus.STATED.getState());
        }
        AssertUtils.isTrue(save(saleChance)==0,"机会数据添加失败！");
    }

    private void checkSaleChanceParams(SaleChance saleChance) {
        AssertUtils.isTrue(StringUtils.isBlank(saleChance.getCustomerName()),"请输入客户名称！");
        AssertUtils.isTrue(StringUtils.isBlank(saleChance.getLinkMan()),"请输入联系人名称！");
        AssertUtils.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()),"请输入联系人电话！");
        AssertUtils.isTrue(!(p.matcher(saleChance.getLinkPhone()).matches()), "手机号格式不合法！");
    }

    /**
     * 更新营销机会数据
     * @param saleChance
     */

    public void updateSaleChance(SaleChance saleChance){
        /**
         * 1、参数的合法性校验
         *      {
         *          客户名称 非空
         *          联系人   非空
         *          联系电话  非空   长度11位   合法手机号码
         *          成功几率  >0
         *      }
         *  2、分配状态
         *      {
         *          如果添加时选择分配人   修改时选择分配人    设置更新时间
         *          如果添加时未选择分配人，修改时选择分配人，设置分配时间
         *          如果添加时未选择分配人，修改时未选择分配人
         *          如果添加时选择分配人，修改时未选择分配人
         *          未选择分配人   state=0    未分配
         *      }
         *  3、设置更新时间
         *  4、记录存在性校验
         *  5、执行更新
         */
        checkSaleChanceParams(saleChance);
//        根据机会数据ID查询记录
        SaleChance temp=queryById(saleChance.getId());
        AssertUtils.isTrue(null==temp,"待更新的数据不存在！");
        if(temp.getState().equals(StateStatus.UN_STATED.getState())&&StringUtils.isNoneBlank(temp.getAssignMan())){
            saleChance.setState(StateStatus.STATED.getState());
            saleChance.setAssignTime(new Date());
        }
        if(!(temp.getState().equals(StateStatus.UN_STATED.getState()))&&StringUtils.isBlank(temp.getAssignMan())){
            saleChance.setState(StateStatus.UN_STATED.getState());
            saleChance.setAssignTime(null);
        }

        saleChance.setUpdateDate(new Date());

        AssertUtils.isTrue(update(saleChance)==0,"机会数据更新失败！");

    }

    /**
     * 删除营销机会数据
     * @param ids
     */
    public void deleteSaleChance(Integer[] ids){
        AssertUtils.isTrue(null==ids||ids.length==0,"请选择待删除的营销机会数据！");
        AssertUtils.isTrue(deleteBatch(ids)!=ids.length,"营销机会数据删除失败！");
    }
}

