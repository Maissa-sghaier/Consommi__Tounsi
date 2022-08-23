export class Product {
    id_product : number;
	bar_code : number;
	name: String;
	description : String;
	image="defaultPicture.jpg";
	poids :number;
	 expDate : Date;
	 price : number;
	 selling_price : number;
	 rating : number;
	 numberOfRatings: number;
	 stock : number;
	 status_promotion : boolean ;
	 promotion_percentage: number;
	 date_beginning_promotion : Date;
	date_end_promotion : Date;
	selling_price_with_promotion : number;
	id_category : number;
	id_provider : number;
}