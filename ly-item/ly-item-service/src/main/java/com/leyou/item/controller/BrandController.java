package com.leyou.item.controller;

import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import com.leyou.pojo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* author: WQM
* description:
*/
@RestController
@RequestMapping("brand")
@Api(description = "商品管理查询接口",value = "商品管理查询接口",tags = {"商品管理查询接口"})  //使用@Api来修饰类
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("page")

    public ResponseEntity<PageResult<Brand>> queryBrandByPages(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc) {

            PageResult<Brand> brandPageResult = brandService.queryBrandByPages(key, page, rows, sortBy, desc);

            if(CollectionUtils.isEmpty(brandPageResult.getItems())){
                ResponseEntity.notFound().build();
            }

        return ResponseEntity.ok(brandPageResult);

    }
    @PostMapping
    @ApiOperation(value = "通过品牌号和分类号 添加品牌",notes = "RestFul风格，两个参数")  //使用@ApiOperation注解来修饰接口
    @ApiImplicitParams({
            @ApiImplicitParam(name="brand",value="品牌号"),
            @ApiImplicitParam(name="cids",value="分类号")})   //使用ApiImplcitParam修饰接口参数
    public  ResponseEntity<Void> saveBrand( Brand brand ,@RequestParam("cids") List<Long> cids){

        brandService.saveBrand(brand,cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
