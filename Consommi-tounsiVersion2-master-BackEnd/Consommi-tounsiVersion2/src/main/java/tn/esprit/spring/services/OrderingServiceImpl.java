package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Cart;
import tn.esprit.spring.entity.CoupleCP;
import tn.esprit.spring.entity.Delivery;
import tn.esprit.spring.entity.DeliveryMethod;
import tn.esprit.spring.entity.Ordering;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.repository.CartRepository;
import tn.esprit.spring.repository.DeliveryMethodRepository;
import tn.esprit.spring.repository.DeliveryRepository;
import tn.esprit.spring.repository.ICoupleCPRepository;
import tn.esprit.spring.repository.ProductRepository;

@Service
public class OrderingServiceImpl implements IOrderingService {
	@Autowired
	CartRepository cart_rep;
	@Autowired
	ICoupleCPRepository coupleCP;
	@Autowired
	ProductRepository product_rep;
	@Autowired
	OrderingServiceImpl ordering;
	@Autowired
	DeliveryMethodRepository delivery_method;
	@Autowired
	DeliveryRepository deliver;
	
	
	@Override
	public int confirm_order(int cart_id){
		/*Cart cart= cart_rep.findById(cart_id).orElse(null);
		cart.setStatus_cart(true);
		List<CoupleCP> listcouple = coupleCP.findByCart(cart);
		for (CoupleCP coupleItem : listcouple){
			Product prod =product_rep.findById(coupleItem.getProduct().getId_product()).orElse(null);
			int stock= prod.getStock();
			int sold= coupleItem.getQuantity();
			if (stock> sold){
			prod.setStock(stock-sold);
			Delivery delivery= new Delivery();
			DeliveryMethod deliveryMethod =new DeliveryMethod(); 
			
			
			}
			else{
				coupleCP.delete(coupleItem);
			}
			
		}*/
		return 1;
		
		
	}

}
