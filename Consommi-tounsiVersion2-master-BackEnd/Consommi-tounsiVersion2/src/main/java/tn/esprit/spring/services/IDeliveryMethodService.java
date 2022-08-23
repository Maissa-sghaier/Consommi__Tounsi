package tn.esprit.spring.services;


import java.util.Optional;


import tn.esprit.spring.entity.DeliveryMethod;


public interface IDeliveryMethodService {
	public DeliveryMethod addDeliveryMethod(DeliveryMethod deliveryMethod);//un admin peut ajouter une methode de livraison 
	
	public DeliveryMethod updateDeliveryMEthod(DeliveryMethod dm);//un admin peut modifier une methode de livraison 
	public void deleteDeliveryMethod(int id);//un admin peut supprimer une methode de livraison 
	
	public Optional<DeliveryMethod> getByIdDeliveryMethod(int id_deliverym);
	 public float CalculateDeliveryFees(String location , String weight , String meansoft);

}
