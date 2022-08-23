package tn.esprit.spring.services;
import java.util.List;
import java.util.Optional;
import tn.esprit.spring.entity.Ad;




public interface IAdService {
	
	public Ad createAd(Ad ad,int idproduct);//l'admin va creer l'ad selon l'id de product 
	
	public Ad updateAd(Ad adv);//un admin va faire les mise à jours des ad
	
	public void deleteAd(Ad ad);//suppression ad 
    public Optional<Ad> getByIdAd(int id);//recherche selon id ad 
    
    List<Ad>getAdByProduct(int id_product);//recherche selon le produit 
    
    
    
    
    
	

	
}
