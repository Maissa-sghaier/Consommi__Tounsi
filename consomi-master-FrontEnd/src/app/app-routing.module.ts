import { CartManagementComponent } from './client-dashboard/cart-management/cart-management.component';
import { ClientDashboardComponent } from './client-dashboard/client-dashboard.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InterfaceAdminComponent } from './interface-admin/interface-admin.component';
import { ProductListComponent } from './client-dashboard/product-list/product-list.component';
import { UpdateProductComponent } from './interface-admin/update-product/update-product.component';
import { CreateProductComponent } from './interface-admin/create-product/create-product.component';
import { UpdateProviderComponent } from './interface-admin/update-provider/update-provider.component';
import { CreateProviderComponent } from './interface-admin/create-provider/create-provider.component';
import { ProductFiltreComponent } from './client-dashboard/product-filtre/product-filtre.component';
import { PassedeventsComponent } from './client-dashboard/passedevents/passedevents.component';
import { ChatDialogComponent } from './client-dashboard/chat-dialog/chat-dialog.component';
import { RegisterInEventComponent } from './client-dashboard/register-in-event/register-in-event.component';
import { LamisMapComponent } from './client-dashboard/lamis-map/lamis-map.component';
import { EventListClientComponent } from './client-dashboard/event-list-client/event-list-client.component';
import { SuccessRegisterEventComponent } from './client-dashboard/success-register-event/success-register-event.component';
import { EventDetailsComponent } from './client-dashboard/event-details/event-details.component';
import { ListParticipantsComponent } from './client-dashboard/list-participants/list-participants.component';
import { CreateCategoryComponent } from './interface-admin/create-category/create-category.component';
import { CategoryListComponent } from './interface-admin/category-list/category-list.component';
import {SecurityComponent} from './client-dashboard/security/security.component';
import { ClientInterfaceComponent } from './client-dashboard/client-interface/client-interface.component';
import { CreateClientComponent } from './client-dashboard/create-client/create-client.component';
import { RetrieveAllDeliverymanComponent } from './deliveryman/retrieve-all-deliveryman/retrieve-all-deliveryman.component';
import { DeliverymanInterfaceComponent } from './deliveryman/deliveryman-interface/deliveryman-interface.component';
import { RetrieveAllClientsComponent } from './client-dashboard/retrieve-all-clients/retrieve-all-clients.component';
//import { RetrieveAllDeliveryManComponent } from './deliveryman/retrieve-all-deliveryman/retrieve-all-deliveryman.component';
const routes: Routes = [
  { path: '', redirectTo: 'client/productList', pathMatch: 'full' },
  { path: 'DashbordAdmin', component:InterfaceAdminComponent, 
children:[ {path:'updateProduct', component: UpdateProductComponent},
{path:'createProduct', component: CreateProductComponent}]},
  { path: 'client', component: ClientDashboardComponent,
  children:[
    {path:'cart', component: CartManagementComponent},
    {path:'productList', component: ProductListComponent},
    {path:'updateProduct', component: UpdateProductComponent},
    {path:'createProduct', component: CreateProductComponent},
    {path:'createCategory', component: CreateCategoryComponent},
    {path:'categoryList', component: CategoryListComponent},
    
    {path:'updateProvider', component: UpdateProviderComponent},
    {path:'createProvider', component: CreateProviderComponent},
    { path: 'Productfitred/:idC/:idP/:r/:promo/:minS/:maxS', component: ProductFiltreComponent },
  {path:'registerInEvent/:idEvent',component:RegisterInEventComponent},
  {path:'chat',component:ChatDialogComponent},
  {path:'maplamis',component:LamisMapComponent},
  {path:'PassedEvents',component:PassedeventsComponent},
  {path:'eventsClient',component:EventListClientComponent},
  {path:'SuccessRegisterEvent',component:SuccessRegisterEventComponent},
  { path: 'getEventDetail/:idEvent', component: EventDetailsComponent },
  {path:'ListParticipants/:idEvent',component:ListParticipantsComponent}





  ]
  },
  {path:'login',component:SecurityComponent},
  {path:'clientinterface/:id', component:ClientInterfaceComponent},
  {path:'createClient',component:CreateClientComponent},
 {path:'listdeliveryman',component:RetrieveAllDeliverymanComponent},
 {path:'deliveryManInterface/:id', component:DeliverymanInterfaceComponent},
 {path:'listclients',component:RetrieveAllClientsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
