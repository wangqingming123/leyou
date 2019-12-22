package com.leyou.item.service;

import com.leyou.item.pojo.Brand;
import com.leyou.pojo.PageResult;

import java.util.List;

public interface BrandService {

    /*
    * 根据条件分页查询品牌信息
    * */
    PageResult<Brand> queryBrandByPages(String key , Integer page, Integer rows, String sortBy, Boolean desc);
    /*
    * 新增品牌信息
    * */
    void saveBrand(Brand brand, List<Long> cids);
}
