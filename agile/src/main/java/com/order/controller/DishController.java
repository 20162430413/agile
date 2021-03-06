package com.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.order.entity.Dish;
import com.order.entity.Page;
import com.order.service.DishCategoryService;
import com.order.service.DishService;
import com.order.util.WebUtil;

/**
 * @author muwei
 * @date 2019/5/3
 */
@RequestMapping(value = "/foreground/dish")
@Controller
public class DishController {

	private final DishService dishService;

	private final DishCategoryService dishCategoryService;

	@Autowired
	public DishController(DishService dishService, DishCategoryService dishCategoryService) {

		this.dishService = dishService;
		this.dishCategoryService = dishCategoryService;
	}

	/**
	 * 查询菜品列表
	 * 
	 * @param categoryId  菜品分类ID
	 * @param currentPage 当前页面
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "dishDisplay.action", method = RequestMethod.GET)
	public String dishDisplay(Integer categoryId, Integer currentPage, Model model, HttpServletRequest request,
			HttpSession session) {
		List<Dish> dishList = new ArrayList<>();
		currentPage = (currentPage == null ? 1 : currentPage);
		System.out.println("currentPage===" + currentPage);
		Page page = new Page(dishService.countDish(categoryId), currentPage, 9);
		page.setPath(WebUtil.getPath(request));
		dishList = dishService.getDishByCategoryId(categoryId, page);
		model.addAttribute("page", page);
		model.addAttribute("categoryId", categoryId == null ? 0 : categoryId);
		model.addAttribute("dishList", dishList);
		return "/foreground/dishDisplay";
	}

}