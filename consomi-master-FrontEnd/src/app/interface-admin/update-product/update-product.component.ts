import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Provider } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Category } from 'src/app/models/categoy';
import { Product } from 'src/app/models/product';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { ProviderService } from 'src/app/services/provider.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  categories: Observable<Category[]>;
  providers: Observable<Provider[]>;

selectedFile : File =null;
  product: Product = new Product();
  p: Product = new Product();
  p2: Product = new Product();

  products :Observable<Product[]>;
  closeResult : String;
  submitted = false;
  public imagePath;
  imgURL: any;
  public message: string;
  public newFileName:string ='';
productToupload : Product=new Product();
productToupload2: Product=new Product();
productIdToupload:number;

  constructor(private http: HttpClient, private modalService: NgbModal, private categoryService: CategoryService, private router: Router,public productService: ProductService,private providerService : ProviderService) { }
    ngOnInit() {

      this.reloadData();
       
      }
      
      reloadData() {
        this.products = this.productService.getProductList();
        this.categories = this.categoryService.getCategoryList();
        this.providers=this.providerService.getProviderList();

      }
      goToList() {
        this.reloadData();
        this.router.navigate(['/updateProduct']);
      }
      deleteProduct(id: number) {
        this.productService.deleteProduct(id)
          .subscribe(
            data => {
              console.log(data);
              this.goToList();
            },
            error => console.log(error));
      }

     
      open(content,p : Product) {
        this.productToupload=p;
      
        this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
          this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
      }
      
      private getDismissReason(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
          return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
          return 'by clicking on a backdrop';
        } else {
          return `with: ${reason}`;
        }
      }
      open2(content,id : number) {
        this.productIdToupload=id;
      
        this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
          this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason2(reason)}`;
        });
      }
      
      private getDismissReason2(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
          return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
          return 'by clicking on a backdrop';
        } else {
          return `with: ${reason}`;
        }
      }

    
      onSubmit() {
        //this.submitted = true;
        //this.save();   
        //this.reloadData();
        this.productService.updateProduct(this.productToupload.id_product,this.productToupload).subscribe((resp)=> {
          console.log(resp);
        }, (err)=> {console.log(err);}
        );
        this.modalService.dismissAll();
        this.reloadData();
         
       
      
      }
desactivateP(id:number){
  this.productService.desactivatePromotion(id).subscribe(
    data => {
      console.log(data);
      this.reloadData();

      this.goToList();
    },
    error => console.log(error));
}

onFileSelected(event){
//this.selectedFile=<File>event.target.files[0];
if (event.target.files.length > 0)
    {
      const file = event.target.files[0];
      this.selectedFile = file;
     // this.f['profile'].setValue(file);
 
    var mimeType = event.target.files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    }
 
    var reader = new FileReader();
    
    this.imagePath = file;
    reader.readAsDataURL(file); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
      this.newFileName=this.imagePath.name;
      console.log(this.newFileName);
      
    }

}}
uploadImg(id: number){
  //var s = i.toString();
//this.s=stringify(i);
  //const fd = new FormData();
  //fd.append('image',this.selectedFile);
 // this.http.put('http://localhost:8081/springMVC/servlet/uploadImageToProduct/', this.productToupload.id_product,fd).subscribe(res=> {
  //  console.log(res);
 // });
this.productService.uploadFile(this.selectedFile).subscribe(
  data => {
    console.log(data);
    //this.newFileName=data.partial;
    

  });
//  error => console.log(error));
  
this.productService.uploadImg2(id,this.newFileName).subscribe(data => {
  console.log(data);
 
});
this.reloadData();
}

}
