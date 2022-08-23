import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Provider } from 'src/app/models/provider';
import { ProviderService } from 'src/app/services/provider.service';

@Component({
  selector: 'app-update-provider',
  templateUrl: './update-provider.component.html',
  styleUrls: ['./update-provider.component.css']
})
export class UpdateProviderComponent implements OnInit {
  provider: Provider = new Provider();
  p: Provider = new Provider();

  providers :Observable<Provider[]>;
  closeResult : String;
  submitted = false;

providerToupload : Provider=new Provider();
  constructor( private modalService: NgbModal, private router: Router,public providerService: ProviderService) { }
    ngOnInit() {

      this.reloadData();
       
      }
      
      reloadData() {
        this.providers = this.providerService.getProviderList();

      }
      goToList() {
        this.reloadData();
        this.router.navigate(['/updateProvider']);
      }
      deleteProvider(id: number) {
        this.providerService.deleteProvider(id)
          .subscribe(
            data => {
              console.log(data);
              this.goToList();
            },
            error => console.log(error));
      }

     
      open(content,p : Provider) {
        this.providerToupload=p;
      
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

    
      onSubmit() {
        //this.submitted = true;
        //this.save();   
        //this.reloadData();
        this.providerService.updateProvider(this.providerToupload.id_provider,this.providerToupload).subscribe((resp)=> {
          console.log(resp);
        }, (err)=> {console.log(err);}
        );
        this.modalService.dismissAll();
        this.reloadData();
         
       
      
      }

     
}

