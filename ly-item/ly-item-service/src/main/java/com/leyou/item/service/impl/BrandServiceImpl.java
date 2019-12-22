package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import com.leyou.pojo.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
/*
* 根据条件分页查询品牌信息
* */
    public PageResult<Brand> queryBrandByPages(String key ,Integer page,Integer rows,String sortBy,Boolean desc) {

        //初始化example 对象
        Example example = new Example(Brand.class);
        //添加模糊查询条件
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + "  " + (desc ? "desc" : "asc"));
        }
        //查询
        List brands = brandMapper.selectByExample(example);
        //包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /*
    * 新增品牌信息
    * */
    @Override
    @Transactional //添加事务
    public void saveBrand(Brand brand, List<Long> cids) {
        brandMapper.insertSelective(brand);

        for (Long cid: cids) {
            brandMapper.insertBrandAndCategory(cid,brand.getId());
        }
    }
}
