package com.talkweb.edu.common.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Descriprion: TODO
 * @Author: zhoutao
 * @Date: 2019/10/29
 **/
public class BaseService<T> {

    @Autowired
    private MyMapper<T> mapper;

    /**
     * 插入
     * @param entity
     * @return
     */
    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    /**
     * 根据实体中的主键删除
     * @param id
     * @return
     */
    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据实体中的主键更新实体中不为空的字段
     * @param entity
     * @return
     */
    public int updateById(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public T selectById(Object id){
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据实体中传入的属性进行查询，获取查询出的第一条数据，若没有数据则返回null
     * @param entity
     * @return
     */
    public T selectOne(T entity) {
        List<T> list = mapper.select(entity);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据实体中传入的属性进行查询
     * @param entity
     * @return
     */
    public List<T> selectByEntity(T entity) {
        return mapper.select(entity);
    }

    /**
     * 查询所有
     * @return
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

}
