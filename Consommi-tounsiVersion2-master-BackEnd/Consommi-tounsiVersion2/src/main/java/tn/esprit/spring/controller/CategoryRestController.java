package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Category;
import tn.esprit.spring.services.ICategoryService;

@RestController
@RequestMapping(path="api/v7/registration")
@CrossOrigin(origins = "http://localhost:4200")

public class CategoryRestController {
	@Autowired
	ICategoryService categorySer;
	
	@PostMapping(value ="/add-category", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addCategory(@RequestBody Category c)
	{
	categorySer.addCategory(c);
	return " done";
	 }
	
	@PutMapping("/update-category")
	@ResponseBody
	public String updateCategory(@RequestBody Category c)
	{
	categorySer.updateCategory(c);
	return " done";
	 }
	
	@DeleteMapping("/remove-category/{id}")
	@ResponseBody
	public String removeCategory(@PathVariable("id") int id)
{
	categorySer.deleteCategory(id);
	return ("done");
	}
	
	@GetMapping("/retrieve-all-categories")
	@ResponseBody
	public List<Category> retreiveCategories ()
	{
		List<Category> list = categorySer.getAllCategory();
		return list;
	}
	@GetMapping("/retrieve-category/{id}")
	@ResponseBody
	public Category retreiveCategoryById (@PathVariable("id") int id)
	{
		return categorySer.getCategoryById(id);
		
	}
	
}
