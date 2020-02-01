package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecService {
    /*
    * 查询规格参数组
    * */
    List<SpecGroup> querySpecByCid(Long cid);

    /*
    * 查询规格参数
    *
    * */

    List<SpecParam> querySpecParams(Long gid);

    void saveSpecGroup(SpecGroup specGroup);

    void updateSpecGroup(SpecGroup specGroup);

    void deleteSpecGroup(Long id);

    void saveSpecParam(SpecParam specParam);

    void updateSpecParam(SpecParam specParam);

    void deleteSpecParam(Long paramId);
}
