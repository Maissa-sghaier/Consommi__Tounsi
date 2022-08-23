package tn.esprit.spring.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Cart;
import tn.esprit.spring.entity.CodePromo;
import tn.esprit.spring.entity.CoupleCP;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.repository.CartRepository;
import tn.esprit.spring.repository.CodePromoRepository;
import tn.esprit.spring.repository.ICoupleCPRepository;
import tn.esprit.spring.repository.ProductRepository;

@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	CartRepository cartrep;
	@Autowired
	ProductRepository productrep;
	@Autowired
	ICoupleCPRepository cart_product;
	@Autowired
	CodePromoRepository codepromo;
	@Autowired 
	IEventService event; 
	
	@Override
	public int delete_cart(int cart_id){
		cartrep.deleteById(cart_id);
		return cart_id;
	}
	@Override
	public int create_cart(){
		Cart cart= new Cart();
		cartrep.save(cart);
		return cart.getId_cart();
	}

	@Override
	public int add_product(int cart_id, int product_id,int quantity){
		int status=0;
		Cart cart= cartrep.findById(cart_id).orElse(null);
		Product p= productrep.findById(product_id).orElse(null);
			
	
			if (p.getStock()<quantity) 
				{status=0;}
			else {
				
			status=1;
			CoupleCP new_coupleCP = new  CoupleCP(quantity,p,cart);
			double price= p.getSelling_price();
			new_coupleCP.setPrice((price*quantity));
			new_coupleCP.setProductname(p.getName());
			new_coupleCP.setProductid(product_id);
			cart_product.save(new_coupleCP);
			int q = p.getStock();
			p.setStock(q-quantity);
			}
		
		return status;
	}
	@Override
	public int update_quantity(int item_id, String product_name,int quantity){
		
		int status=0;
		
		
		CoupleCP couple_CP = cart_product.findById(item_id).orElse(null);
		Product p= couple_CP.getProduct();
			if (p.getStock()<quantity) 
				{status=0;}
			else {
				
			status=1;
			int q= couple_CP.getQuantity();
			quantity =quantity+ q;
			couple_CP.setQuantity(quantity);
			double price= p.getSelling_price();
			couple_CP.setPrice((price*quantity));
			cart_product.save(couple_CP);
			int qq = p.getStock();
			p.setStock(qq-quantity);
			}
		
		return status;
	}
	
	@Override
	public void delete_product(int id_couple){
		
		CoupleCP couple_CP = cart_product.findById(id_couple).orElse(null);
		Product p= couple_CP.getProduct();
		int q= couple_CP.getQuantity();
		cart_product.delete(couple_CP);
		int qq = p.getStock();
		p.setStock(q+qq);
	}
	@Override
	public List<CoupleCP> show_cart(int cart_id){
		
		Cart cart= cartrep.findById(cart_id).orElse(null);
		List<CoupleCP> Listcouple_CP = cart_product.findByCart(cart);
		return Listcouple_CP;
	}
	
	@Override
	public double calulate_fees(int cart_id, int eventId, int code_promo ){
		double fees= 0;
	
		String code_result = null;
		Map<String , Double> code_fees = new HashMap <String, Double>();
		Cart cart= cartrep.findById(cart_id).orElse(null);
		
		for (CoupleCP coupleItem : cart_product.findByCart(cart)){
			double price= coupleItem.getPrice();
			fees = fees +price;
			code_result="total fees";
				}
		if (eventId>0){
			fees =  fees + event.findbyId(eventId).getCostEvent();
		}
		if (code_promo>0){
			CodePromo codeval= codepromo.findById(code_promo).orElse(null);
			if (codeval!=null){
				if (codeval.isStatus_CodePromo()== true)
				{ fees= fees*codeval.getPercentage();
					code_result="success";
				}
				else { code_result="invalide";}
			}
			code_result="nonexistant";
		}
		code_fees.put(code_result, fees);
		cart.setTotal(fees);
		return fees;
		
	}
		
}			
				
			

		
	