import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Routes, Server} from "../utils/ServerRoutes";
import {Observable} from "rxjs/Observable";
import {Recept} from "../model/Recept";
import "rxjs/add/operator/map";

@Injectable()
export class ReceptService {

  constructor(private http: Http) { }

getReceptek(): Observable<Recept[]> {
  return this.http.get(Server.routeTo(Routes.RECEPTEK),{withCredentials: true})
    .map(res => res.json())
}

create(recept: Recept): Observable<Recept> {
  return this.http.post(Server.routeTo(Routes.RECEPTEK), recept)
    .map(res => res.json())
}


read(id: number) {
  return this.http.get(Server.routeTo(Routes.RECEPTEK) + '/' + id)
    .map(res => res.json())
}

update(recept: Recept) {
  return this.http.put(Server.routeTo(Routes.RECEPTEK) + '/' + recept.id, recept)
    .map(res => res.json())
}


}
