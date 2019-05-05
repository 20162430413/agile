package com.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.entity.Customer;
import com.order.service.CustomerService;

/**
 * 
 * @author muwei
 * @date 2019/4/25
 *
 */
@Controller
@RequestMapping(value="/foreground/customer")
public class CustomerController{

	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 客户登录
	 * @param account 登录账号
	 * @param password 密码
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String account,String password,HttpSession session,Model model) {
//		System.out.println(account + "====" + password);
		Customer customer = customerService.login(account, password);
		if(customer != null) {
			session.setAttribute("customer", customer);
			return "redirect:/foreground/home.jsp";
		}else {
			model.addAttribute("msg", "账号或密码错误！");
			return "foreground/login";
		}
		
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("logout.action")
	public String logout(HttpSession session) {
		session.removeAttribute("customer");
		return "foreground/login";
	}
	
	/**
	 * 顾客注册
	 * @param account 注册使用的账号 一般为电话号码
	 * @param password 密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public String register(@RequestParam("account") String account,
						   @RequestParam("password") String password, Model model) {
		boolean success = customerService.register(account, password);
		if (success) {
			return "redirect:/foreground/login.jsp";
		} else {
			model.addAttribute("msg", "注册失败");
			return "/foreground/register";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/phoneIsExist")
	public Integer phoneIsExist(@RequestParam("account") String account) {
		List<Customer> customerList = new ArrayList<Customer>();
		if(account != null) {
			customerList = customerService.getByPhone(account);
		}
		return customerList.size();
	}
	
	
}
