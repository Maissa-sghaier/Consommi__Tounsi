import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientDashboardComponent } from './client-dashboard/client-dashboard.component';
import { HeaderComponent } from './client-dashboard/header/header.component';
import { FooterComponent } from './client-dashboard/footer/footer.component';
import { FormsModule } from '@angular/forms';
import { CartManagementComponent } from './client-dashboard/cart-management/cart-management.component';
import { ProductListComponent } from './client-dashboard/product-list/product-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UpdateProductComponent } from './interface-admin/update-product/update-product.component';
import { CreateProductComponent } from './interface-admin/create-product/create-product.component';
import { UpdateProviderComponent } from './interface-admin/update-provider/update-provider.component';
import { CreateProviderComponent } from './interface-admin/create-provider/create-provider.component';
import { UpdateCodePromoComponent } from './interface-admin/update-code-promo/update-code-promo.component';
import { CreateCodePromoComponent } from './interface-admin/create-code-promo/create-code-promo.component';
import { CreateCategoryComponent } from './interface-admin/create-category/create-category.component';
import { CategoryListComponent } from './interface-admin/category-list/category-list.component';
import { ProductFiltreComponent } from './client-dashboard/product-filtre/product-filtre.component';
import { PassedeventsComponent } from './client-dashboard/passedevents/passedevents.component';
import { RegisterInEventComponent } from './client-dashboard/register-in-event/register-in-event.component';
import { LamisMapComponent } from './client-dashboard/lamis-map/lamis-map.component';
import { EventListClientComponent } from './client-dashboard/event-list-client/event-list-client.component';
import { SuccessRegisterEventComponent } from './client-dashboard/success-register-event/success-register-event.component';
import { EventDetailsComponent } from './client-dashboard/event-details/event-details.component';
import { ListParticipantsComponent } from './client-dashboard/list-participants/list-participants.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import { ClientService } from './services/client.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { CreateClientComponent } from './client-dashboard/create-client/create-client.component';
import { SecurityComponent } from './client-dashboard/security/security.component';
import { ClientInterfaceComponent } from './client-dashboard/client-interface/client-interface.component';
import { CreateDeliverymanComponent } from './deliveryman/create-deliveryman/create-deliveryman.component';
//import { RetrieveAllDeliveryManComponent } from './deliveryman/retrieve-all-deliveryman/retrieve-all-deliveryman.component';
import { DeliverymanService } from './services/deliveryman.service';
import { Observable } from 'rxjs';
import { RetrieveAllDeliverymanComponent } from './deliveryman/retrieve-all-deliveryman/retrieve-all-deliveryman.component';
import { DeliverymanInterfaceComponent } from './deliveryman/deliveryman-interface/deliveryman-interface.component';
import { RetrieveAllClientsComponent } from './client-dashboard/retrieve-all-clients/retrieve-all-clients.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientDashboardComponent,
    HeaderComponent,
    FooterComponent,
    CartManagementComponent,
    ProductListComponent,
    UpdateProductComponent,
    CreateProductComponent,
    UpdateProviderComponent,
    CreateProviderComponent,
    UpdateCodePromoComponent,
    CreateCodePromoComponent,
    CreateCategoryComponent,
    CategoryListComponent,
    ProductFiltreComponent,
    PassedeventsComponent,
    RegisterInEventComponent,
    LamisMapComponent,
    EventListClientComponent,
    SuccessRegisterEventComponent,
    EventDetailsComponent,
    ListParticipantsComponent,
    CreateClientComponent,
    SecurityComponent,
    ClientInterfaceComponent,
    CreateDeliverymanComponent,
    RetrieveAllDeliverymanComponent,
    DeliverymanInterfaceComponent,
    RetrieveAllClientsComponent,
    HomeComponent,
   // RetrieveAllDeliveryManComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,
  ],
  providers: [ClientService,DeliverymanService],
  bootstrap: [AppComponent]
})
export class AppModule { 
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available
        
       

        return next.handle(request);
    }}