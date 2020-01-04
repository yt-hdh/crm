package crmxx.service;

import base.BaseService;
import crmxx.db.dao.ModuleMapper;
import crmxx.dto.TreeDto;
import crmxx.po.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Autowired
    private ModuleMapper moduleMapper;

    public List<TreeDto> queryModules(){

        return moduleMapper.queryModules();
    }
}
