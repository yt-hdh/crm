package crmxx.db.dao;

import base.BaseMapper;
import crmxx.dto.TreeDto;
import crmxx.po.Module;

import java.util.List;
public interface ModuleMapper extends BaseMapper<Module,Integer> {

    public List<TreeDto> queryModules();
}