package tn.esprit.spring.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.CodePromo;
import tn.esprit.spring.entity.CoupleCP;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.repository.CodePromoRepository;
import tn.esprit.spring.services.ICartService;
import tn.esprit.spring.services.IProductService;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="api/v6/registration")
@RestController
public class CartController {
	@Autowired 
	ICartService cart_service;
	@Autowired
	IProductService product_service;
	@Autowired
	CodePromoRepository codepr;

	//http://localhost:8082/api/v6/registration/createCart
	@PostMapping(value="/createCart")
	@ResponseBody
	public int createCart(){
		return cart_service.create_cart();
	}
	//http://localhost:8082/api/v6/registration/deleteCart/3
	@DeleteMapping(value="/deleteCart/{id}")
	@ResponseBody
	public void deleteCart(@PathVariable("id") int cart_id ){
		cart_service.delete_cart(cart_id);
	}
	
	//http://localhost:8082/api/v6/registration/addproduct/1/1/3
	@PostMapping(value="/addproduct/{id_cart}/{id_product}/{quantity}")
	@ResponseBody
	public int  addproduct(@PathVariable("id_cart")int cart_id, @PathVariable("id_product")int product_id,@PathVariable("quantity")int quantity ){
		return cart_service.add_product(cart_id, product_id, quantity);
	}
	
	
	//http://localhost:8082/api/v6/registration/updateproduct/1/1/5
	@PutMapping(value="/updateproduct/{item_id}/{product_name}/{quantity}")
	@ResponseBody
	public int  updateproduct(@PathVariable("item_id")int item_id, @PathVariable("product_name")String product_name,@PathVariable("quantity")int quantity ){
		return cart_service.update_quantity(item_id, product_name, quantity);
	}
	//http://localhost:8082/api/v6/registration/delete_product_from_cart/1/1
	@DeleteMapping(value="/delete_product_from_cart/{cp_id}")
	@ResponseBody
	public void delete_product_from_cart(@PathVariable("cp_id")int cp_id){
		cart_service.delete_product(cp_id);
	}
	
	//http://localhost:8082/api/v6/registration/showCart/1
	@GetMapping(value="/showCart/{id_Cart}")
	@ResponseBody
	public List<CoupleCP> showCart(@PathVariable("id_Cart") int cart_id){
		return cart_service.show_cart(cart_id);
		}
	//http://localhost:8082/api/v6/registration/get_fees/1/0/0
	@GetMapping(value="/get_fees/{cart_id}/{eventId}/{code_promo}")
	@ResponseBody
	public double get_fees(@PathVariable("cart_id")int cart_id ,@PathVariable("eventId") int event_id,@PathVariable("code_promo") int cdpromo){
		return cart_service.calulate_fees(cart_id, event_id, cdpromo);
	}
	//http://localhost:8082/api/v6/registration/addproduct
	@PostMapping(value="/addproduct")
	@ResponseBody
	public void addproduct(){
		Product p= new Product();
		p.setBar_code(619);
		p.setName("dress");
		p.setStock(90);
		p.setPoids(100);
		p.setSelling_price(90000);
		product_service.addProduct(p);
	}
	//http://localhost:8082/api/v6/registration/addcodepromo
		@PostMapping(value="/addcodepromo")
		@ResponseBody
		public void addcodepromo(){
			CodePromo p= new CodePromo(1, "Maissa", true);
			codepr.save(p);
		}

}	
	