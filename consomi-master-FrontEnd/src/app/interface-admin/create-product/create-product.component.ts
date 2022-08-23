import { Component, OnInit, Provider } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Category } from 'src/app/models/categoy';
import { Product } from 'src/app/models/product';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { ProviderService } from 'src/app/services/provider.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  product: Product = new Product();
  submitted = false;
  public imagePath;
  imgURL: any;
  public message: string;
  selectedFile : File =null;
  categories: Observable<Category[]>;
  providers: Observable<Provider[]>;
  constructor(public productService : ProductService, private router: Router,private categoryService: CategoryService,private providerService : ProviderService) { }

  ngOnInit(): void {
    this.categories = this.categoryService.getCategoryList();
    this.providers=this.providerService.getProviderList();

  }

 


  newProduct():void{
    this.submitted = false;
    this.product = new Product();
  }
  save() {
    this.productService.createProduct(this.product).subscribe(data => {
      console.log(data)
     this.product = new Product();
   
    }, error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
   this.save();   
    this.router.navigate(['/updateProduct']);
 
  }

 


 
}
