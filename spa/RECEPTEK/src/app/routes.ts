import {Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {Role} from "./model/User";
import {RouteGuard} from "./route.guard";
import {RegisterComponent} from "./pages/register/register.component";
import {SajatreceptekComponent} from "./pages/sajatreceptek/sajatreceptek.component";
import {ReceptekComponent} from "./pages/receptek/receptek.component";
import {UserlistComponent} from "./pages/userlist/userlist.component";


export const appRoutes: Routes = [
  {
    path: '',
    canActivateChild: [RouteGuard],
    children: [
      {path: '', redirectTo: 'help', pathMatch: 'full'},
      {path: 'login', component: LoginComponent, data: {roles: [Role.GUEST]}},
      {path: 'register', component: RegisterComponent, data: {roles: [Role.GUEST]}},
      {path: 'sajatreceptek', component: SajatreceptekComponent},
      {path: 'receptek', component: ReceptekComponent},
      {path: 'users', component: UserlistComponent}
    ]
  }];
