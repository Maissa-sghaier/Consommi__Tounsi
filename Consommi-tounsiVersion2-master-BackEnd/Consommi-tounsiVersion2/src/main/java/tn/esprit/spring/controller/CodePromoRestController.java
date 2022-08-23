package tn.esprit.spring.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import tn.esprit.spring.entity.CodePromo;
import tn.esprit.spring.services.ICodePromoService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="api/v7/registration")

public class CodePromoRestController {
	@Autowired
ICodePromoService codePromoSer;
	
	@PostMapping("/add-codePromo")
	@ResponseBody
	public String addCodePromo(@RequestBody CodePromo c)
	{
		codePromoSer.addCodePromo(c);
		return " done";
	 }
	
	@PutMapping("/update-codePromo")
	@ResponseBody
	public String updateCodePromo(@RequestBody CodePromo c)
	{
		codePromoSer.updateCodePromo(c);
	return " done";
	 }
	
	@DeleteMapping("/remove-codePromo/{id}")
	@ResponseBody
	public String removeCodePromo(@PathVariable("id") int id)
{	codePromoSer.deleteCodePromo(id);
	return ("done");
	}
	
	@GetMapping("/retrieve-all-codePromo")
	@ResponseBody
	public List<CodePromo> retreiveAllCodePromo ()
	{
		List<CodePromo> list = codePromoSer.getAllCodePromo();
		return list;
	}
	@GetMapping("/retrieve-codePromo/{id}")
	@ResponseBody
	public CodePromo retreiveCategoryById (@PathVariable("id") int id)
	{
		return codePromoSer.getCodePromoById(id);
		
	}
	@PutMapping("/activate-codePromo/{id}")
	@ResponseBody
	public String activateCodePromo(@PathVariable("id") int id)
	{
		return codePromoSer.activatePromo(id);
	}

	@PutMapping("/desactivate-codePromo/{id}")
	@ResponseBody
	public String desactivateCodePromo(@PathVariable("id") int id)
	{
		return codePromoSer.desactivatePromo(id);
	}
	@PutMapping("/desactivate-codePromo-unavailable")
	@ResponseBody
	public String desactivateCodePromoUnavailable()
	{
		return codePromoSer.desactivatePromoUnavailable();
	}
	
	@GetMapping("/getcodepromo/{codename}")
	@ResponseBody
	public CodePromo getCode(@PathVariable("codename") String codename){
		return codePromoSer.getCode(codename);
	}
	
	
}
