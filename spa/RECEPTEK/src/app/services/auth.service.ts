import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {User} from "../model/User";
import {Routes, Server} from "../utils/ServerRoutes";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthService {
  user: User;
  isLoggedIn: boolean = false;

  constructor(private http: Http) {
    this.user = new User();
  }

  login(user: User) {
    return this.http.post(Server.routeTo(Routes.LOGIN), user, {withCredentials: true})
      .map(res => {
        this.isLoggedIn = true;
        this.user = res.json();
        return this.user;
      })
  }

  logout() {
    return this.http.get(Server.routeTo(Routes.LOGOUT))
      .map(res => {
        this.user = new User();
        this.isLoggedIn = false;
      })
  }

  register(user: User) {
    return this.http.post(Server.routeTo(Routes.REGISTER), user, {withCredentials: true})
      .map(res => {
        this.isLoggedIn = true;
        this.user = res.json();
        return this.user;
      })
  }

  delete(id: number) {
    return this.http.delete(Server.routeTo(Routes.USERS) + '/' + id, {withCredentials: true})
  }


  getUsers(): Observable<User[]> {
    return this.http.get(Server.routeTo(Routes.USERS),{withCredentials: true})
      .map(res => res.json())
  }


}
