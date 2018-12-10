package com.pyg.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.mapper.TbBrandMapper;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrandExample;
import com.pyg.pojo.TbBrandExample.Criteria;
import com.pyg.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private TbBrandMapper brandMapper;

	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);

	}
	
	//分页查询
	@Override
	public PageResult findPage(int page, int rows) {
		PageHelper.startPage(page, rows);
		Page<TbBrand> brandPage = (Page<TbBrand>) brandMapper.selectByExample(null);
		return new PageResult(brandPage.getTotal(), brandPage.getResult());
	}

	// 模糊查询
	@Override
	public PageResult search(TbBrand brand, int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);//分页	
		
		TbBrandExample example=new TbBrandExample();
		
		Criteria criteria = example.createCriteria();
		if(brand!=null){
			if(brand.getName()!=null && brand.getName().length()>0){
				criteria.andNameLike("%"+brand.getName()+"%");
			}
			if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
				criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
			}			
		}
		
		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
		
		return new PageResult(page.getTotal(), page.getResult());
	}

	// 保存 ，添加和修改
	@Override
	public Result save(TbBrand brand) {
		if (brand.getId() != null) {
			try {
				brandMapper.updateByPrimaryKey(brand);
				return new Result(true, "修改成功");
			} catch (Exception e) {

				return new Result(false, "修改失败");
			}
		} else {
			try {
				brandMapper.insert(brand);
				return new Result(true, "添加成功");
			} catch (Exception e) {
				return new Result(false, "添加失败");
			}
		}
	}

	// 删除
	@Override
	public void del(Long[] ids) {

		for (long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}

	}

}
