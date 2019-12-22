package com.leyou.item.service;

import com.leyou.item.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> queryCategoryByPid(Long pid);
}
