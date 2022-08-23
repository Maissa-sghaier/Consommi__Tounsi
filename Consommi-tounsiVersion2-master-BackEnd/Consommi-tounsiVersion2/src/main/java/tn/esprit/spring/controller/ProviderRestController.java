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
import tn.esprit.spring.entity.Provider;
import tn.esprit.spring.services.IProviderService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="api/v7/registration")
public class ProviderRestController {
	@Autowired
IProviderService providerSer;
	@PostMapping("/add-provider")
	@ResponseBody
	public String addProvider(@RequestBody Provider p)
	{
		providerSer.addProvider(p);
		return " done";
	 }
	@PutMapping("/update-provider/{id}")
	@ResponseBody
	public String updateProvider(@PathVariable("id") int id,@RequestBody Provider p)
	{
		Provider p1 = providerSer.updateProvider(id,p);
		return " done";
	 }
	
	@DeleteMapping("/remove-provider/{id}")
	@ResponseBody
	public String removeProvider(@PathVariable("id") int id)
{	
		providerSer.deleteProvider(id);
	return ("done");
	}
	@GetMapping("/retrieve-all-providers")
	@ResponseBody
	public List<Provider> retreiveAllProviders ()
	{
		List<Provider> list = providerSer.getAllProvider();
		return list;
	}
	@GetMapping("/retrieve-provider/{id}")
	@ResponseBody
	public Provider retreiveProviderById (@PathVariable("id") int id)
	{
		return providerSer.getProviderById(id);
		
	}
	
}
