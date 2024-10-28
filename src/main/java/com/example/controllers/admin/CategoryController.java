package com.example.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Category;
import com.example.models.CategoryModel;
import com.example.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	// model dùng để trả lại dữ liệu cho view	
	@RequestMapping("")
	public String all(Model model ) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("list", list);
		return "admin/category/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		CategoryModel category = new CategoryModel();
		category.setIsEdit(false);
		model.addAttribute("category", category);
		return "admin/category/add";
	}
	//  Model Map trả dữ liệu về cho view	
	//	@Vlaid kiểm tra dữ liệu trong Model
	//	@ModelAttribute("category") CategoryModel cateModel :: lấy dữ liệu từ view
	//  tương đương trong Model, BindingResult result là quá trình tự động:
	//  ánh xạ từ view đến model, các trường giống nhau thì binding được,đưa tham
	//  vào kiểm tra
	
	// 	BeanUtils.copyProperties(cateModel,entity);
	//  Vì trong entity không có isEdit nên phải đẩy dữ liệu lên Model , sau đó
	//  mới trả lại vào entity ( nếu có cùng tên trường dữ liệu )
	
	// Nếu dùng servelet cũ phải cài thêm thư viện BeanUtils 
	@PostMapping("/save")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("category") CategoryModel cateModel, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/category/add");
		}
		Category entity = new Category();
		// copy tu Model sang Entiry
		BeanUtils.copyProperties(cateModel, entity);
		// goi ham save trong service
		categoryService.save(entity);
		// dua thong bao ve cho bien message
		String message="";
		if(cateModel.getIsEdit() == true) {
			message="Category is edited!!!!!!";
		}else {
			message="Category is saved!!!!!!!";
		}
		model.addAttribute("message", message);
		// redirect ve URL controller
		// trong ModelAndView co 3 hanh dong : forward, redirect
		// cái view nhận cái forward này là list
		return new ModelAndView("forward:/admin/categories",model);
	}
	// @PathVariable("id") Long categoryId
	// có thể không cần khai báo biến Long categoryId mà dùng
	// dùng luôn biến id
	
	// hàm findById(categoryId) trả về một Optional
	// spring boot cung cấp phương thức để kiểm tra sự tồn tại
	// nếu không dùng optional thì dùng đối tượng như bình thường
	
	// Khi edit lấy dữ liệu từ DB lên entity, từ entity vào model, từ model lên view
	@GetMapping("/edit/{id}")
	public ModelAndView edit(ModelMap model,@PathVariable("id") Long categoryId) {
		Optional<Category> optCategory = categoryService.findById(categoryId);
		CategoryModel cateModel = new CategoryModel();
		//kiểm tra sự tồn tại của category
		if(optCategory.isPresent()) {
			Category entity = optCategory.get();
			// copy từ entity sang cateModel
			BeanUtils.copyProperties(entity, cateModel);
			cateModel.setIsEdit(true);
			// đẩy dữ liệu ra view
			model.addAttribute("category", cateModel);
			return new ModelAndView("admin/category/add",model);
		}
		model.addAttribute("message", "Category is not existed!!!!");
		return new ModelAndView("forward:/admin/categories",model);
	}
	@GetMapping("delete/{id}")
	public ModelAndView delete(ModelMap model, @PathVariable("id") Long categoryId) {
		categoryService.deleteById(categoryId);
		model.addAttribute("message", "Category is deleted!!!!");
		return new ModelAndView("forward:/admin/categories",model);
	}
}
