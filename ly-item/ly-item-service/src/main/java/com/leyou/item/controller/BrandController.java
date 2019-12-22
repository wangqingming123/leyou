package com.leyou.item.controller;

import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import com.leyou.pojo.PageResult;
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
    public  ResponseEntity<Void> saveBrand( Brand brand ,@RequestParam("cids") List<Long> cids){

        brandService.saveBrand(brand,cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
