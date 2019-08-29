package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import com.leyou.myexception.ExceptionEnum;
import com.leyou.myexception.LeyouException;
import com.leyou.vo.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean descending, String searchValue) {
        // 分页
        PageHelper.startPage(page,rows);
        // 过滤
        Example example = new Example(Brand.class);
        if(StringUtils.isNotBlank(searchValue)){
            example.createCriteria().orLike("name","%"+searchValue+"%").orEqualTo("letter",searchValue.toUpperCase());
        }
        // 排序
        if(StringUtils.isNotBlank(sortBy)){
            String orderByClause=sortBy+(descending?" DESC":" ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        List<Brand> brandsList = brandMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(brandsList)){
            throw new LeyouException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //解析分页结果
        PageInfo<Brand>  info=new PageInfo<>(brandsList);
        return new PageResult<>(info.getTotal(),info.getPages(),brandsList);
    }
}
