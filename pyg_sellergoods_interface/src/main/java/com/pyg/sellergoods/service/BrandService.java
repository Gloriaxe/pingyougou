package com.pyg.sellergoods.service;

import java.util.List;

import com.pyg.pojo.TbBrand;

import entity.PageResult;
import entity.Result;

public interface BrandService {

	List<TbBrand> findAll();
	
	//模糊查询
	PageResult search(TbBrand brand,int page,int rows);
	
	PageResult findPage(int page,int rows);
	
	Result save(TbBrand brand);
	
	void del(Long[] ids);
}
