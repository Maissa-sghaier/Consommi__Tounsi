package tn.esprit.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//import java.util.Optional;

//import tn.esprit.spring.entity.AdView;
import tn.esprit.spring.entity.Delivery;
import tn.esprit.spring.entity.DeliveryMan;
import tn.esprit.spring.entity.DeliveryZone;
import tn.esprit.spring.entity.Ordering;
import tn.esprit.spring.entity.StatusOrder;
import tn.esprit.spring.exeptions.ResourceNotFoundException;
import tn.esprit.spring.repository.IDeliveryManRepository;
import tn.esprit.spring.repository.DeliveryRepository;
import tn.esprit.spring.repository.OrderingRepository;
import tn.esprit.spring.services.DeliveryService;
//import tn.esprit.spring.exeptions.ResourceNotFoundException;
import java.text.DateFormat;
//import java.util.Date;


import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;


@RestController

public class DeliveryRestController {
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
	
	
	
	@Autowired
	DeliveryRepository deliveryRep ;
	
	@Autowired
	OrderingRepository orderRep ;

	@Autowired
	IDeliveryManRepository deliveryManRep ;
	@Autowired
	DeliveryService deliveryservice;
	
	@GetMapping("/delivery")
	public List<Delivery> getAllDelivery() {
		return deliveryRep.findAll();
	}
	
	@PostMapping("/Delivery/{id_order}/{address}")
	public Delivery CreateDelivery(@PathVariable(value = "id_order")int id_order,
	 @PathVariable(value = "address")String address,@RequestBody Delivery delivery1) throws ResourceNotFoundException {
		System.out.println("start CreateDelivery ");
		if(id_order!=0 ) {
			Ordering ord=null;
			ord=orderRep.findById(id_order).get();
			System.out.println("OrderMouna ---> "+ord.toString());
			Delivery delivery ;
			if(ord.getDelivery()!=null){
				
				System.out.println("delivery ---> "+ord.getDelivery().toString());
				delivery= ord.getDelivery();
			}
			else {
				delivery = new Delivery();
			}
			System.out.println("value of enum "+DeliveryZone.valueOf(address.trim()));
			delivery.setStatus_order(StatusOrder.delivered);
			delivery.setOrder(ord);
			delivery.setDate_delivery(new Date());
			delivery.setActual_adress(delivery1.getActual_adress());
			
			
			delivery.setDelivery_zone(DeliveryZone.valueOf(address.trim()));
			if(!DeliveryZone.valueOf(address.trim()).equals(DeliveryZone.shop)){
				
				List<DeliveryMan> lstDvMan=deliveryManRep.findAll();
				for(DeliveryMan devMan : lstDvMan) {
					if (devMan.getAddress().toString().equals(address)) {
						delivery.setDeliveryman(devMan);
						break;
					
					
					}
				
			}
			}
				deliveryRep.save(delivery);
				ord.setDelivery(delivery);
				orderRep.save(ord);
				return 	delivery;
			
			
		}
			return null;
		
	}
	@GetMapping("/Delivery/Client/{id_user}")
	public ResponseEntity<List<Delivery>> getDeliveryClient(@PathVariable(value = "id_user") int id_user)
			throws ResourceNotFoundException {
		List<Delivery> deliverys=deliveryservice.GetDeliveryByClient(id_user).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id Client :: " + id_user));
		return ResponseEntity.ok().body(deliverys);
	}
	
	
	@GetMapping("/Deliverys/{id_delivery}")
	public String getAdressByDeliveryId(@PathVariable(value = "id_delivery") int id_delivery)
			throws ResourceNotFoundException {
		List<String> info = deliveryRep.FindDeliveryAdress(id_delivery);
		List<String> info1=deliveryRep.FindDeliveryStatus(id_delivery);
		
			
			
			return("votre collis est pris en charge par notre service de collecte,il est actuellement à:"+info.get(0)+""+" statut livraison:"+info1.get(0));
			
		}
	@GetMapping(value = "/genrateAndDownloadQRCode/{id_delivery}/{codeText}/{width}/{height}")
	public void download(
			@PathVariable("id_delivery") Integer id_delivery,
			@PathVariable("codeText") String codeText,
			@PathVariable("width") Integer width,
			@PathVariable("height") Integer height)
	         
		    throws Exception {
	Date aujourdhui = new Date();
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.SHORT,
		        DateFormat.SHORT);

		        
				DeliveryService.generateQRCodeImage(deliveryservice.getAdressStatusByDeliveryId(id_delivery)+ ",date:"+shortDateFormat.format(aujourdhui), width , height  , QR_CODE_IMAGE_PATH);
		    }

	@GetMapping(value = "/genrateQRCode/{id_delivery}/{codeText}/{width}/{height}")
	public ResponseEntity<byte[]> generateQRCode(
			@PathVariable("id_delivery") Integer id_delivery,
			@PathVariable("codeText") String codeText,
			@PathVariable("width") Integer width,
			@PathVariable("height") Integer height)
		    throws Exception {
		Date aujourdhui = new Date();
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.SHORT,
		        DateFormat.SHORT);
		
		        return ResponseEntity.status(HttpStatus.OK).body(DeliveryService.getQRCodeImage(deliveryservice.getAdressStatusByDeliveryId(id_delivery)+ ",date:"+shortDateFormat.format(aujourdhui) , width, height));
		    }
	
	
	
	
	
	}
	


