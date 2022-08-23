package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.services.ICategoryService;
import tn.esprit.spring.services.IProductService;
import tn.esprit.spring.webcam.IWebcamQRCodeExample; 
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="api/v7/registration")
public class ProductRestController {
	@Autowired
IProductService productService;		
	@Autowired
	ICategoryService categorySer;
	@Autowired
	IWebcamQRCodeExample webcamService;
	@Autowired  ServletContext context;

	
	
	@PostMapping("/add-product")
		@ResponseBody
		public String addCodePromo(@RequestBody Product p) throws InterruptedException
	{
		String s= webcamService.call();
		if ((s!=null)&&(productService.getProductByBarCode(Long.parseLong(s)).isEmpty()))
//		if (s!=null)
			{
			p.setBar_code(Long.parseLong(s));
			productService.addProduct(p);
			
		//}
			return "Product is added succesfully";
		}
		else 
		{ 
//			if (productService.getProductByBarCode(Long.parseLong(s))!=null)
//				return " product already exists in database";
//			else 
				return "product is not added to database";
		
	 }}
	@PutMapping("/uploadImageToProduct/{idP}/{name}")
	@ResponseBody
	public String uploadFileToProduct(@PathVariable("idP") int idP,@PathVariable("name") String name) throws IllegalStateException, IOException
	{	Product p= productService.getProductById(idP);
		p.setImage(name);
		productService.updateProduct(idP,p);

		return "image uploaded succesfully";
		
	}
	
	@PutMapping("/uploadImageToProduct3")
	@ResponseBody
	public String uploadFileToProduct3(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException
	{	
	 System.out.println("Ok .............");
     boolean isExit = new File(context.getRealPath("/Images/")).exists();
     if (!isExit)
     {
     	new File (context.getRealPath("/Images/")).mkdir();
     	System.out.println("mk dir.............");
     }
     String filename = file.getOriginalFilename();
     String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
    
     File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
     try
     {
     	System.out.println("Image");
     	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
     	 
     }catch(Exception e) {
     	e.printStackTrace();
     }

return newFileName ;    }
	
	@PutMapping("/activate-product-promotion/{id}/{percentage}/{dB}/{dE}")
	// http://localhost:8082/springMVC/servlet/activate-product-promotion/3/20/26-03-2021/30-03-2021
	@ResponseBody
	public String activateProductPromotion(@PathVariable("id") int id,@PathVariable("percentage")float percentage,@PathVariable("dB")String dB,@PathVariable("dE") String dE) throws ParseException
	{
	return productService.activateProductPromotion(id, percentage, dB, dE);
		 }
	
	@PutMapping("/desactivate-promotion/{id}")
	@ResponseBody
	public String desactivatePromotion (@PathVariable("id") int id)
	{
		return productService.desactivateProductPromotion(id);
	}

	@DeleteMapping("/remove-product/{id}")
	@ResponseBody
	public String removeProduct(@PathVariable("id") int id)
{	
		productService.deleteProduct(id);
	return ("done");
	}
	
		
		@GetMapping("/retrieve-all-products")
		@ResponseBody
		public List<Product> retreiveProducts ()
		{
			List<Product> list = productService.retrieveProductByPriceDesc();
			return list;
		}

		@GetMapping("/retrieve-all-products-between/{pinf}/{psup}")
		@ResponseBody
		public List<Product> retreiveProductsBetween (@PathVariable("pinf") int pinf,@PathVariable("psup") int psup)
		{
			List<Product> list = productService.getByPriceBetween(pinf, psup);
			return list;
		}
		
		
		@PutMapping("/update-product/{id}")
		@ResponseBody
		public String updateProd (@PathVariable("id") int id,@RequestBody Product p)
		{
			Product p1 = productService.updateProduct(id,p);
			return " done";
		}
		 @GetMapping(path="/Imgarticles/{id}")
		 @ResponseBody
		 public byte[] getPhoto2(@PathVariable("id") int id) throws Exception{
			 Product product   = productService.getProductById(id);
			 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+product.getImage()));
		 }
		
	@PutMapping("/set-productCategory/{idP}/{idC}")
	@ResponseBody
	public String SetCategoryForProduct (@PathVariable("idP") int idP,@PathVariable("idC") int idC)
	{	
		productService.setProductCategory(idP, idC);
		return "done";
	}
	
	@PutMapping("/set-productProvider/{idP}/{idp2}")
	@ResponseBody
	public String SetProviderForProduct (@PathVariable("idP") int idP,@PathVariable("idp2") int idp2)
	{	
		productService.setProductProvider(idP, idp2);
				return "done";
	}

	@PutMapping("/update-promotion-automatic")
	@ResponseBody
	public String updatePromotions ()
	{
		return productService.desactivateProductPromotionAutomatic();
	}	

	
	@GetMapping("/filtre/{idC}/{idProv}/{rates}/{promo}/{minSellingPrice}/{maxSellingPrice}")
	@ResponseBody
	public List<Product> filterProducts (@PathVariable("idC") int idC,@PathVariable("idProv") int idProv,@PathVariable("rates") int rates,@PathVariable("promo") int promo, @PathVariable("minSellingPrice") double minSellingPrice,@PathVariable("maxSellingPrice") double maxSellingPrice)
	{
		List<Product> list = productService.filtre(idC, idProv, rates, promo, minSellingPrice, maxSellingPrice);
		return list;
	}
	@PutMapping("/rate-a-product/{idP}/{rate}")
	@ResponseBody
	public String RateAProduct (@PathVariable("idP") int idP, @PathVariable("rate") int rate)
	{
		 productService.RateAProduct(idP, rate);
		 return "rated";
	}
	
}