package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


import tn.esprit.spring.entity.DeliveryMethod;
import tn.esprit.spring.exeptions.ResourceNotFoundException;
import tn.esprit.spring.repository.DeliveryMethodRepository;
import tn.esprit.spring.services.DeliveryMethodService;
@CrossOrigin(origins="http://localhost:4200") 
@RequestMapping(path="api/v8/registration")

@RestController

public class DeliveryMethodRestController {
	@Autowired
	DeliveryMethodService deliverym;
	@Autowired
	DeliveryMethodRepository delivery_method;
	
	@GetMapping("/calculFees/{id_deliverym}/{Distance}/{Weight}")
	public float CalculateDeliveryFees(@PathVariable("id_deliverym") int id_deliverym,@PathVariable("Distance") float Distance,@PathVariable("Weight") float Weight){
		float distanceFee = 0 ;
		float  weightFee = 0 ;
		float  meansOfTransportFees = 0 ;
		
		if (Distance > 100) {
			distanceFee = (float) ((Distance * 0.1) + 3);
		} else {
			distanceFee = (float) (Distance * 0.1);
		}
		
		if (Weight > 20) {
			 weightFee = (float) ((Weight * 0.1) + 2);
		} else {
			 weightFee = (float) (Weight * 0.1);
		}
		
		if ((Weight < 5)&(Distance < 5)) {
			 meansOfTransportFees = (float) 2;
			 
		} else {
			
			 meansOfTransportFees = (float) 4;
		}
		
		return( (float)  weightFee + distanceFee +  meansOfTransportFees);
		
	}
	
	
	@GetMapping("/delivermethod")
	public List<DeliveryMethod> getAllDeliverM() {
		return delivery_method.findAll();
	}
	
	@GetMapping("/findDeliverym/{id_deliverym}")
	@ResponseBody
	public ResponseEntity<DeliveryMethod> getByIdDeliveryMethod(@PathVariable(value = "id_deliverym") int id_deliverym)throws ResourceNotFoundException{
		System.out.println("getByIdDeliveryMethod");
		DeliveryMethod dm = deliverym.getByIdDeliveryMethod(id_deliverym)
				.orElseThrow(() -> new ResourceNotFoundException("deliverymethod not found for this id :: " +id_deliverym ));
		return ResponseEntity.ok().body(dm);
		
	}
	
	//creation de delivery method 
	@PostMapping("/addDeliveryMethod")
	@ResponseBody
	public  DeliveryMethod  addDeliveryMethod(@RequestBody DeliveryMethod dm)
	{
		System.out.println("add delivery method");
		deliverym.addDeliveryMethod(dm);
		return dm;
	}
	
	
	@PutMapping("/modify_deliverym/{id_deliverym}")
	
	public ResponseEntity<DeliveryMethod> updateDeliveryMethod(@PathVariable(value = "id_deliverym") int id_deliverym,
			 @RequestBody DeliveryMethod DM) throws ResourceNotFoundException {
		System.out.println("updateDeliveryMethod");
		DeliveryMethod deliverymeth = delivery_method.findById(id_deliverym)
				.orElseThrow(() -> new ResourceNotFoundException("DeliveryMethod not found for this id :: " + id_deliverym));
		prepObj(DM, deliverymeth);

		final DeliveryMethod modify_deliverym=delivery_method.save(deliverymeth);
		
		return ResponseEntity.ok(modify_deliverym);
	}
	public void prepObj(DeliveryMethod DM,DeliveryMethod deliverymeth) {

		if (DM.getId()!=0) {
			deliverymeth.setId(DM.getId());
		}
		if (DM.getFees()!=0) {
			deliverymeth.setFees(DM.getFees());
		}
		if (!DM.getType().equals("")&& DM.getType()!=null) {
			deliverymeth.setType(DM.getType());
		}
	}
	
	
	@DeleteMapping("/deleteDeliveryMethod/{id_deliverym}")
	@ResponseBody
	public void deleteDeliveryMethod(@PathVariable("id_deliverym") int id_deliverym)
	{
		System.out.println("deleteDeliveryMethod");
		deliverym.deleteDeliveryMethod(id_deliverym);
	}
	

}
