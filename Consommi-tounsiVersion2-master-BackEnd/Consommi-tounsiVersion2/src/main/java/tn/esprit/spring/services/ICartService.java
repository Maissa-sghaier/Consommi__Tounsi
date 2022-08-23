package tn.esprit.spring.services;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.entity.Cart;
import tn.esprit.spring.entity.CoupleCP;
import tn.esprit.spring.entity.Product;

public interface ICartService {




	int delete_cart(int cart_id);
	
	int create_cart();

	int add_product(int cart_id, int product_id, int quantity);

	
	double calulate_fees(int cart_id, int eventId, int code_promo);


	List<CoupleCP> show_cart(int cart_id);

	void delete_product(int id_couple);

	int update_quantity(int item_id, String product_name, int quantity);

	
}
