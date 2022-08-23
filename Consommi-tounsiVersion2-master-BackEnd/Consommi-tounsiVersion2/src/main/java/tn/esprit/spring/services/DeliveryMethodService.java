package tn.esprit.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DeliveryMethod;
import tn.esprit.spring.repository.DeliveryMethodRepository;

@Service

public class DeliveryMethodService implements IDeliveryMethodService {
	@Autowired
	DeliveryMethodRepository DeliveryMethodRepository ;

	@Override
	public  DeliveryMethod  addDeliveryMethod(DeliveryMethod d) {
		// TODO Auto-generated method stub
		return  DeliveryMethodRepository.save(d);
		 
		
	}

	@Override
	public DeliveryMethod updateDeliveryMEthod(DeliveryMethod dm) {
		// TODO Auto-generated method stub
		return DeliveryMethodRepository.save(dm);
	}

	@Override
	public void deleteDeliveryMethod(int id) {
		// TODO Auto-generated method stub
		 DeliveryMethodRepository.deleteById(id);
		
	}

	@Override
	public Optional<DeliveryMethod> getByIdDeliveryMethod(int id_deliverym) {
		// TODO Auto-generated method stub
		return DeliveryMethodRepository.findById(id_deliverym);
	}
      //calculate delivery fees
	@Override
	public float CalculateDeliveryFees(String location, String weight, String meansOfTransport) {
		// TODO Auto-generated method stub
		float Distance = 0 ;
		float Weight = 0 ;
		float distanceFee = 0 ;
		float weightFee = 0 ;
		float meansOfTransportFees = 0 ;

		if (Distance > 100) {
			distanceFee = (float) ((Distance * 0.1) + 3);
		} else {
			distanceFee = (float) (Distance * 0.1);
		}
		
		if (Weight > 20) {
			weightFee = (float) (((Weight * 0.1) + 2));
		} else {
			weightFee = (float) (Weight* 0.1);
		}
		
		if(((Weight < 5)&(Distance < 5))) {
			meansOfTransport = "motorcycle";
			meansOfTransportFees = (float) 2;
		} else {
			meansOfTransport = "car";
			 meansOfTransportFees = (float) 4;
		}
		
		return (float) (weightFee + distanceFee + meansOfTransportFees) ;
	}
 

}
