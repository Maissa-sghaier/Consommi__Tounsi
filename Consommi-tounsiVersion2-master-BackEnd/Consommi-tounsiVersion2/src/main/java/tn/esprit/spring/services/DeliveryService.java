package tn.esprit.spring.services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Delivery;
import tn.esprit.spring.services.QRCodeGenerator;
//import tn.esprit.spring.entity.DeliveryZone;
//import tn.esprit.spring.repository.DeliveryManRepository;
import tn.esprit.spring.repository.DeliveryRepository;
//import tn.esprit.spring.repository.OrderMounaRepository;
import tn.esprit.spring.repository.DeliveryRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service

public class DeliveryService implements IDeliveryService {
	Connection Conn;
    Statement stm;

	@Autowired DeliveryRepository deliveryrep;
	
	

	@Override
	public Optional<List<Delivery>> GetDeliveryByClient(int id_user) {
		// TODO Auto-generated method stub
		
		Optional<List<Delivery>> deliv=deliveryrep.findDeliveryByClient(id_user);
		return deliv;
	}



	@Override
	public String getAdressStatusByDeliveryId(int id_delivery) {
		// TODO Auto-generated method stub
		List<String> info = deliveryrep.FindDeliveryAdress(id_delivery);
		List<String> info1=deliveryrep.FindDeliveryStatus(id_delivery);
		return("votre colis est pris en charge par notre service de collecte , il est actuellement à:"+info.get(0)+""+" ,statut livraison:"+info1.get(0));
		
		
	}
	
	public static void generateQRCodeImage(String text,int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
       
    }
	
	
	public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	
	
	
}
		
	


