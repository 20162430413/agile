package com.order.service;

import java.util.List;

import com.order.entity.Dish;
import com.order.entity.Page;

public interface DishService {

	/**
	 * 查询所有的菜品信息
	 * 
	 * @param page 分页信息
	 * @return
	 */
	public List<Dish> getAll(Page page);

	public int deleteByPrimaryKey(Integer dishId);

	public int insert(Dish record);

	public int insertSelective(Dish record);

	public Dish selectByPrimaryKey(Integer dishId);

	/**
	 * 通过菜品分类查找菜品列表
	 * 
	 * @param categoryId 菜品分类ID
	 * @param page       分页信息
	 * @return
	 */
	public List<Dish> getDishByCategoryId(Integer categoryId, Page page);

	/**
	 * 查询当前分类 的菜品数量
	 * @param categoryId 分类编号
	 * @return
	 */
	public int countDish(Integer categoryId);

	public int updateByPrimaryKeySelective(Dish record);

	public int updateByPrimaryKey(Dish record);

}
