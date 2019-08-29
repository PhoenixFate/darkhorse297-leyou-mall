package com.leyou.item.web;


import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import com.leyou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param descending
     * @param searchValue
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value="page",defaultValue = "1")Integer page,
            @RequestParam(value="rows",defaultValue = "5")Integer rows,
            @RequestParam(value="sortBy",required = false)String sortBy,
            @RequestParam(value="descending",defaultValue = "false")Boolean descending,
            @RequestParam(value="searchValue",required = false)String searchValue
    ){
        PageResult<Brand> result= brandService.queryBrandByPage(page,rows,sortBy,descending,searchValue);
        return ResponseEntity.ok(result);
    }


}
