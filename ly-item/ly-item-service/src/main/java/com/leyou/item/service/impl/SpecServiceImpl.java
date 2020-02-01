package com.leyou.item.service.impl;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;


    @Override
    public List<SpecGroup> querySpecByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        SpecParam specParam = new SpecParam();
        List<SpecGroup> groups = specGroupMapper.select(specGroup);
        return groups;
    }

    @Override
    public List<SpecParam> querySpecParams(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);

        //select方法具有通用性，只有当属性有值时才会根据该属性去查询
       return specParamMapper.select(specParam);
    }

    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        specGroupMapper.insertSelective(specGroup);
    }

    @Override
    public void updateSpecGroup(SpecGroup specGroup) {
        specGroupMapper.updateByPrimaryKeySelective(specGroup);
    }

    @Override
    @Transactional
    public void deleteSpecGroup(Long id) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(id);
        specParamMapper.delete(specParam);
        specGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveSpecParam(SpecParam specParam) {
        specParamMapper.insertSelective(specParam);
    }

    @Override
    public void updateSpecParam(SpecParam specParam) {
        specParamMapper.updateByPrimaryKey(specParam);
    }

    @Override
    public void deleteSpecParam(Long paramId) {
        specParamMapper.deleteByPrimaryKey(paramId);
    }
}
