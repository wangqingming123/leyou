package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("category")
@Api(description = "商品分类查询接口",value = "商品分类查询接口",tags = {"商品分类查询接口"})  //使用@Api来修饰类
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("list")
    @ApiOperation(value = "通过pid 查询商品分类",notes = "RestFul风格，1个参数")  //使用@ApiOperation注解来修饰接口
    @ApiImplicitParams({
            @ApiImplicitParam(name="pid",value="品牌号")})   //使用ApiImplcitParam修饰接口参数
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        if (pid == null || pid < 0) {
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryCategoryByPid(pid);
        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("111");
        return ResponseEntity.ok(categories);
    }
}
