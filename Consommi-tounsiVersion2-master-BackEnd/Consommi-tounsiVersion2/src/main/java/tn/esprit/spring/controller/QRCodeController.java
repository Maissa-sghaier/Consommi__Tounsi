package tn.esprit.spring.controller;


import java.text.DateFormat;
import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.services.QRCodeGenerator;

@RestController

public class QRCodeController {
	
	
/*private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

	
@GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
public void download(
		@PathVariable("codeText") String codeText,
		@PathVariable("width") Integer width,
		@PathVariable("height") Integer height)
         
	    throws Exception {
Date aujourdhui = new Date();
	DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
	        DateFormat.SHORT,
	        DateFormat.SHORT);

	        QRCodeGenerator.generateQRCodeImage("votre collis numero 11655 est pris en charge par notre service de collecte,il est actuellement à ben_arous,date:"+shortDateFormat.format(aujourdhui)+"statut livraison:en_cours_de_livraison", width , height  , QR_CODE_IMAGE_PATH);
	    }

@GetMapping(value = "/genrateQRCode/{codeText}/{width}/{height}")
public ResponseEntity<byte[]> generateQRCode(
		@PathVariable("codeText") String codeText,
		@PathVariable("width") Integer width,
		@PathVariable("height") Integer height)
	    throws Exception {
	Date aujourdhui = new Date();
	DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
	        DateFormat.SHORT,
	        DateFormat.SHORT);
	
	        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage("votre collis numero 11655 est pris en charge par notre service de collecte,il est actuellement à ben_arous,date:"+shortDateFormat.format(aujourdhui)+"statut livraison:en_cours_de_livraison" , width, height));
	    }*/

}
