package com.pyg.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbBrand;
import com.pyg.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;


@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference
	private BrandService brandService;
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		return brandService.findAll();
	}
	
	@RequestMapping("/findPage")
	public PageResult findPage(int page,int rows) {
		return brandService.findPage(page,rows);
	}
	
	@RequestMapping("/save")
	public Result save(@RequestBody TbBrand brand) {	

		return	brandService.save(brand);
		
	}
	@RequestMapping("/delete")
	public Result del(Long[] ids) {
		try {
			brandService.del(ids);
			return new Result(true,"删除成功");
		} catch (Exception e) {
			return new Result(false,"删除失败");
		}
	}
	
	//模糊查詢
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbBrand brand,int page,int rows){
		return brandService.findPage(brand, page, rows);		
	}
	
	@RequestMapping("/findOne")
	public TbBrand findOne(Long id) {
		return brandService.findOne(id);
	}
	
	
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList() {
		return brandService.selectOptionList();
	}
}
