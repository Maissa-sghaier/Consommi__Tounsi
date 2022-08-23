package tn.esprit.spring.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Product;


public interface IProductService {
	public int addProduct(Product product);  //by admin
	public Product updateProduct(int id,Product product);  //by admin
    public void deleteProduct( int id); //by admin
    public List<Product> getAllProducts(); 
    public Product retrieveProductByName(String name); 
    public Product getProductById(int id);
    public List<Product> getAllProductsWithPromotion( boolean b); 
    public List<Product> retrieveProductByPriceAsc(); 
    public List<Product> retrieveProductByPriceDesc(); 
    public String activateProductPromotion (int id,float percentage, String dB,String dE) throws ParseException;
    public String desactivateProductPromotion (int id);
    
    public String desactivateProductPromotionAutomatic ();
    public String NotificationExpiredProducts ();

    public void setProductCategory(int idP, int idC);
    public List<Product> getProductsByCategory(int idC); 
    public void setProductProvider(int idP, int idp2);
    public void RateAProduct (int idP, int rate);
    public void uploadFile(MultipartFile file, int idP) throws IllegalStateException, IOException;
    public List<Product> getProductByBarCode(long bar_code);
    public List<Product> getByPriceBetween(@Param("binf") float binf, @Param("bsup") float bsup);

    public List<Product> filtre(int idC,int idProv,int rates ,int promo,double minSellingPrice, double maxSellingPrice);

}
