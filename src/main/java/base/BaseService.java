package base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract  class BaseService<T, ID> {

    @Autowired
    private BaseMapper<T,ID> baseMapper;

    /**
     * 添加记录  返回受影响行数
     * @param entity
     * @return
     */
    public int save(T entity){
        return baseMapper.save(entity);
    }

    /**
     * 添加记录  返回主键
     * @param entity
     * @return
     */
    public ID saveHasKey(T entity){
        baseMapper.saveHasKey(entity);
        ID id=null;
        try{
//          反射获取
            id= (ID) entity.getClass().getDeclaredField("id").get(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 批量添加记录  返回受影响行数
     * @param entities
     * @return
     */
    public int saveBatch(List<T> entities){

        return baseMapper.saveBatch(entities);
    }

    /**
     * 记录详情查询
     * @param id
     * @return
     */
    public T queryById(ID id){
        return baseMapper.queryById(id);
    }

    /**
     * 多添加列表查询
     * @param baseQuery
     * @return
     */
    public List<T> queryByParams(BaseQuery baseQuery){
        return baseMapper.queryByParams(baseQuery);
    }

    /**
     * 分页查询
     * @param baseQuery
     * @return
     */
    public PageInfo<T> queryByParamForPage(BaseQuery baseQuery){
        PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
        return new PageInfo<T>(baseMapper.queryByParams(baseQuery));
    }

    /**
     * 单条记录更新
     * @param entity
     * @return
     */
    public int update(T entity){
        return baseMapper.update(entity);
    }

    /**
     * 批量记录更新
     * @param map
     * @return
     */
    public int updateBatch(Map<String,Object> map){
        return baseMapper.updateBatch(map);
    }

    /**
     * 单条记录删除
     * @param id
     * @return
     */
    public int delete(ID id){
        return baseMapper.delete(id);
    }

    /**
     * 批量记录删除
     * @param ids
     * @return
     */
    public int deleteBatch(ID[] ids){
        return baseMapper.deleteBatch(ids);
    }

    /**
     * easy分页查询
     * @param baseQuery
     * @return
     */
    public Map<String,Object> queryParamBydata(BaseQuery baseQuery){
//        查询分页数据
        PageInfo<T> page= queryByParamForPage(baseQuery);
//        创建结果集接受数据
        Map<String,Object> result=new HashMap<>();
        result.put("total",page.getTotal());
        result.put("rows",page.getList());
//        返回存有数据的结果集
        return result;
    }

}
