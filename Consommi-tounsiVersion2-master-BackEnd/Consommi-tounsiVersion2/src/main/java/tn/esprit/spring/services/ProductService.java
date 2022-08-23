package tn.esprit.spring.services;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.Product;

import tn.esprit.spring.entity.Provider;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ProductRepository;

@Service
public class ProductService implements IProductService {
	@Autowired
	ProductRepository productRep;
	@Autowired
	ICategoryService cs;
	@Autowired
	IProviderService ps;
	
	@Override
	public int addProduct(Product product) {
		productRep.save(product);
		return product.getId_product();
	}

	@Override
	public Product updateProduct(int id,Product product) {
		Product prodToUp =getProductById(id);
		prodToUp.setId_product(product.getId_product());
		prodToUp.setName(product.getName());
		prodToUp.setBar_code(product.getBar_code());
		prodToUp.setExpDate(product.getExpDate());
		prodToUp.setDate_beginning_promotion(product.getDate_beginning_promotion());
		prodToUp.setDate_end_promotion(product.getDate_end_promotion());
		prodToUp.setId_category(product.getId_category());
		prodToUp.setId_provider(product.getId_provider());
		prodToUp.setDescription(product.getDescription());
		prodToUp.setNumberOfRatings(product.getNumberOfRatings());
		prodToUp.setRating(product.getRating());
		prodToUp.setPoids(product.getPoids());
		prodToUp.setImage(product.getImage());
		//prodToUp.setImageExtension(product.getImageExtension());
		prodToUp.setPrice(product.getPrice());
		prodToUp.setSelling_price(product.getSelling_price());
		double x = product.getSelling_price();
		x = x -(x*(product.getPromotion_percentage())/100);
		prodToUp.setSelling_price_with_promotion(x);
		prodToUp.setPromotion_percentage(product.getPromotion_percentage());
		prodToUp.setStatus_promotion(product.isStatus_promotion());
		prodToUp.setStock(product.getStock());
		productRep.save(prodToUp);
		return prodToUp;
	}

	@Override
	public void deleteProduct(int id) {
		productRep.findById(id).orElse(null);
		productRep.deleteById(id);
		
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		productRep.findAll().forEach(p-> products.add(p));
		return products;
	}

	@Override
	public Product getProductById(int id) {
		 return productRep.findById(id).get();		
	}

	@Override
	public Product retrieveProductByName(String name) {
		return productRep.retrieveProductByName(name);
		
	}

	@Override
	public String activateProductPromotion(int id,float percentage, String dB,String dE) throws ParseException {
		Product p = getProductById(id);
		if (p.isStatus_promotion()==false)
		{   p.setStatus_promotion(true);
			p.setPromotion_percentage(percentage);
			Date dB1 = new SimpleDateFormat("dd-MM-yyyy").parse(dB);
			Date dE1 = new SimpleDateFormat("dd-MM-yyyy").parse(dE);
			p.setDate_beginning_promotion(dB1);
			p.setDate_end_promotion(dE1);
			double x = p.getSelling_price();
			x = x -(x*(p.getPromotion_percentage())/100);
			p.setSelling_price_with_promotion(x);
			productRep.save(p);
			return "Promotion of "+ p.getName()+" is activated";
		}
		else  return "Promotion of "+ p.getName()+" is already activated ";
	}

	@Override
	public String desactivateProductPromotion(int id ) {
		Product p = getProductById(id);
		if (p.isStatus_promotion()==true)
		{   p.setStatus_promotion(false);
			p.setPromotion_percentage(0);
			p.setDate_beginning_promotion(null);
			p.setDate_end_promotion(null);
			p.setSelling_price_with_promotion(0);
			productRep.save(p);
			return "Promotion of "+ p.getName()+" is desactivated";
		}
		else  return "Promotion of "+ p.getName()+" is already desactivated ";		
	}

	@Override
	public List<Product> getAllProductsWithPromotion(boolean b) {
		return productRep.retrieveProductByPromotion(b);
	}

	@Override
	public List<Product> retrieveProductByPriceAsc() {
		List<Product> products = new ArrayList<Product>();
		productRep.findAll().forEach(p-> products.add(p));
		Collections.sort(products);
		return products;
	}

	@Override
	public List<Product> retrieveProductByPriceDesc() {
		List<Product> products = new ArrayList<Product>();
		productRep.findAll().forEach(p-> products.add(p));
		Collections.sort(products, Collections.reverseOrder());
		return products;
	}

	@Override
	public void setProductCategory(int idP,int idC) {
		Product p = getProductById(idP);
			Category c = cs.getCategoryById(idC);
			p.setId_category(idC);
		p.setCategory(c);
		productRep.save(p);
		}

	@Override
	public List<Product> getProductsByCategory( int idC) {
		return productRep.retrieveProductsByCategory(cs.getCategoryById(idC));
	}

	@Override
	public void setProductProvider(int idP, int idp2) {
		Product p = getProductById(idP);
		Provider prov = ps.getProviderById(idp2);
		p.setId_provider(idp2);
		p.setProvider(prov);
	productRep.save(p);		
	}

	@Override
	@Scheduled(cron = "0 0 00 * * ?")
	public String desactivateProductPromotionAutomatic() {
		List<Product> products = getAllProductsWithPromotion(true);
		for (Product p : products){
			
		LocalDate tn= LocalDate.now();
        Date timeNow = Date.from(tn.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date timeEnd = p.getDate_end_promotion();
		
		if( timeNow.compareTo(timeEnd)== 1 || timeNow.compareTo(timeEnd)==0 )
		    {
			 desactivateProductPromotion(p.getId_product());			
		    }
		}
		return "All product promotions are updated";
	}

	@Override
	@Scheduled(cron = "0 0 12 * * ?")

	public String NotificationExpiredProducts() {
		List<Product> products= getAllProducts();
		List<Product> expiredProducts =new ArrayList<Product>();
		for (Product p : products){
			LocalDate tn= LocalDate.now();
	        Date timeNow =Date.from(tn.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    Date d= p.getExpDate();
		    if( timeNow.compareTo(d)== 1 || timeNow.compareTo(d)==0 )
		    { expiredProducts.add(p);
		    }
		}
		//notification process
		return "admin notified";
	}

	@Override
	public void RateAProduct(int idP, int rate) {
		Product p = getProductById(idP);
		int n= p.getNumberOfRatings();
		float x = p.getRating();
		p.setRating(((x*n)+ rate)/(n+1));
		p.setNumberOfRatings(n+1);
		productRep.save(p);
	}

	
	
	
	@Override
	public List<Product> filtre(int idC, int idProv, int rates,int promo, double minSellingPrice, double maxSellingPrice) {
		List<Product> prods = productRep.findAll();
		List<Product> toRemove = new ArrayList<Product>();
		if(idC!=0)
		{
			for (Product p : prods) 
			{
				if (p.getId_category()!= idC)
					toRemove.add(p);
			}
			prods.removeAll(toRemove);
		}
		
		 if(idProv!=0)
				{		
			        List<Product> toRemove1 = new ArrayList<Product>();

					for (Product p : prods) 
						{
							if (p.getId_provider()!= idProv)
								toRemove1.add(p);						}
					prods.removeAll(toRemove1);

				}
        if(rates!=0)
		              {			List<Product> toRemove2 = new ArrayList<Product>();

			            for (Product p : prods) 
			               {
				              if (p.getRating()!= rates)
					          toRemove2.add(p);				}
		
			                 prods.removeAll(toRemove2);
                      }
		
		 if(promo!=0)
		                         {List<Product> toRemove3 = new ArrayList<Product>();
			   		              for (Product p : prods) 
			   		               {
			   		                if (p.isStatus_promotion()==false)
			   			        	toRemove3.add(p);
			   		               }
			   		             prods.removeAll(toRemove3);
		   		                 }
		             			if((minSellingPrice!=0)&&(maxSellingPrice!=0)&&(promo!=0))
			   			          {List<Product> toRemove4 = new ArrayList<Product>();
			   				       for (Product p : prods) 
			   					     {
			   						    if ((p.getSelling_price_with_promotion()>= minSellingPrice )&&(p.getSelling_price_with_promotion()<= maxSellingPrice) )
			   							toRemove4.add(p);
			   					      }
					   		       prods=toRemove4;
			   			          }
			   		   
		   				            if((minSellingPrice!=0)&&(maxSellingPrice!=0)&&(promo==0))
		   				               	{List<Product> toRemove5 = new ArrayList<Product>();
		   						          for (Product p : prods) 
		   							       { if (p.isStatus_promotion()==true)
		   							          {if ((p.getSelling_price_with_promotion()> minSellingPrice )&&(p.getSelling_price_with_promotion()<maxSellingPrice))
					   							   toRemove5.add(p);}
		   							       else {if ((p.getSelling_price()>= minSellingPrice )&&(p.getSelling_price()<=maxSellingPrice) )
				   							   toRemove5.add(p);}
					   					 }
						   		       prods=toRemove5;

		   					             }
		      
		return prods;
	}

	@Override
	public void uploadFile(MultipartFile file, int idP) throws IllegalStateException, IOException {
		
		file.transferTo(new File("C:\\Users\\NOUR\\git\\Consommi-tounsi3\\Consommi-tounsi\\src\\main\\resources\\productImgs\\"+ getProductById(idP).getBar_code()+"."+FilenameUtils.getExtension(file.getOriginalFilename())));
	}

	@Override
	public List<Product> getProductByBarCode(long bar_code) {
		if (productRep.findAll().isEmpty())
		{return null;
		}
		return productRep.getProductByBarCode(bar_code);

	}

	@Override
	public List<Product> getByPriceBetween(float binf, float bsup) {
		return productRep.getByPriceBetween(binf, bsup);
	}




	
}