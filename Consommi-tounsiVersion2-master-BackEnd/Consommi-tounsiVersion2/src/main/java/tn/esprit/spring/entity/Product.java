package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Product implements Serializable, Comparable<Product>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_product;
	private long bar_code=0;
	private String name;
	private String description;
	private String image="url.png";
	private float poids;
	private Date expDate;
	private double price;
	private double selling_price;
	private float rating=0;
	private int numberOfRatings=0;
	private int stock;
	private boolean status_promotion ;
	private float promotion_percentage;
	private Date date_beginning_promotion;
	private Date date_end_promotion;
	private double selling_price_with_promotion;
	private int id_category=0;
	private int id_provider=0;
	private int nbr_complaint=0;

	
	@JsonBackReference(value="product-provider")
	@ManyToOne
	 private Provider provider;

	@JsonBackReference(value="product-category")
	@ManyToOne
	private Category category;
	
	@JsonManagedReference(value="coupleCP-product")
	@OneToMany(mappedBy="product")
	private List<CoupleCP> listCartPoducts;
	 
	
	public List<CoupleCP> getListCartPoducts() {
		return listCartPoducts;
	}

	public void setListCartPoducts(List<CoupleCP> listCartPoducts) {
		this.listCartPoducts = listCartPoducts;
	}
	
	@ManyToMany(mappedBy="products",cascade= CascadeType.REMOVE)
	private List<Ad> Ads;
	

	public List<Ad> getAds() {
		return Ads;
	}

	public void setAds(List<Ad> ads) {
		Ads = ads;
	}

	public Product() {
		super();
	}

	public int getId_product() {
		return id_product;
	}


	public void setId_product(int id_product) {
		this.id_product = id_product;
	}


	public long getBar_code() {
		return bar_code;
	}


	public void setBar_code(long bar_code) {
		this.bar_code = bar_code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getPoids() {
		return poids;
	}


	public void setPoids(float poids) {
		this.poids = poids;
	}
	
	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getSelling_price() {
		return selling_price;
	}


	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public boolean isStatus_promotion() {
		return status_promotion;
	}


	public void setStatus_promotion(boolean status_promotion) {
		this.status_promotion = status_promotion;
	}


	public float getPromotion_percentage() {
		return promotion_percentage;
	}


	public void setPromotion_percentage(float promotion_percentage) {
		this.promotion_percentage = promotion_percentage;
	}


	public Date getDate_beginning_promotion() {
		return date_beginning_promotion;
	}


	public void setDate_beginning_promotion(Date date_beginning_promotion) {
		this.date_beginning_promotion = date_beginning_promotion;
	}


	public Date getDate_end_promotion() {
		return date_end_promotion;
	}


	public void setDate_end_promotion(Date date_end_promotion) {
		this.date_end_promotion = date_end_promotion;
	}


	public Provider getProvider() {
		return provider;
	}


	public void setProvider(Provider provider) {
		this.provider = provider;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int compareTo(Product o) {
		if(this.getSelling_price() <o.getSelling_price())
	          return -1;
	    else if(o.getSelling_price()<this.getSelling_price())
	          return 1;
	    return 0;
	}

	public double getSelling_price_with_promotion() {
		return selling_price_with_promotion;
	}

	public void setSelling_price_with_promotion(double selling_price_with_promotion) {
		this.selling_price_with_promotion = selling_price_with_promotion;
	}

	public int getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public int getId_provider() {
		return id_provider;
	}

	public void setId_provider(int id_provider) {
		this.id_provider = id_provider;
	}

	public int getNbr_complaint() {
		return nbr_complaint;
	}

	public void setNbr_complaint(int nbr_complaint) {
		this.nbr_complaint = nbr_complaint;
	}

	
	



	
	
	
	
}
