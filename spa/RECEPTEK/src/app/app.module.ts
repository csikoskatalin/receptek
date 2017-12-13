import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import {RouterModule} from '@angular/router'
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "./services/auth.service";
import {HttpModule} from "@angular/http";
import {MaterialItemsModule} from "./MaterialItemsModule";
import  {appRoutes} from './routes';
import {RouteGuard} from "./route.guard";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { RegisterComponent } from './pages/register/register.component';
import { MenuComponent } from './pages/menu/menu.component';
import { ReceptekComponent } from './pages/receptek/receptek.component';
import { SajatreceptekComponent } from './pages/sajatreceptek/sajatreceptek.component';
import { ReceptdetailsComponent } from './pages/receptdetails/receptdetails.component';
import {ReceptService} from "./services/recept.service";
import { UserlistComponent } from './pages/userlist/userlist.component';
import {MatCardModule} from '@angular/material/card';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MenuComponent,
    ReceptekComponent,
    SajatreceptekComponent,
    ReceptdetailsComponent,
    UserlistComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    MaterialItemsModule,
    BrowserAnimationsModule,
    MatCardModule


  ],
  providers: [AuthService, ReceptService, RouteGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
