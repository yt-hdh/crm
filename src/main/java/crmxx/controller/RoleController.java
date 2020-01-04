package crmxx.controller;

import base.BaseController;
import crmxx.model.ResultInfo;
import crmxx.po.Role;
import crmxx.query.RoleQuery;
import crmxx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role";
    }

    /**
     * 查询所有角色（user页面）
     * @return
     */
    @RequestMapping("allRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(){
        return roleService.queryAllRoles();
    }

    /**
     * 角色页面展示
     * @param page
     * @param rows
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryByParams(@RequestParam(defaultValue = "1")Integer page,
                                            @RequestParam(defaultValue = "10") Integer rows,
                                            RoleQuery roleQuery){
        roleQuery.setPageNum(page);
        roleQuery.setPageSize(rows);
        return roleService.queryParamBydata(roleQuery);
    }

    /**
     * 角色添加
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveRole(Role role){
        roleService.saveRole(role);
        return success();
    }

    /**
     * 角色更新
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success();
    }

    /**
     * 角色删除
     * @param roleId
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer roleId){
        roleService.deleteRoleByRoleId(roleId);
        return success();
    }
}
