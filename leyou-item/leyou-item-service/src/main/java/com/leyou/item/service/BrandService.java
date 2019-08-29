package com.leyou.item.service;


import com.leyou.item.pojo.Brand;
import com.leyou.vo.PageResult;

public interface BrandService {
    PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean descending, String searchValue);
}
