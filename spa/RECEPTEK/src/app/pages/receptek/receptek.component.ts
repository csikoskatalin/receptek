import { Component, ViewChild, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {ReceptService} from "../../services/recept.service";
import {Recept} from "../../model/Recept";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-receptek',
  templateUrl: './receptek.component.html',
  styleUrls: ['./receptek.component.css']
})
export class ReceptekComponent implements OnInit {
  displayedColumns = ['id', 'name', 'status'];
  dataSource;


  constructor(private authService: AuthService, private router: Router,private receptService: ReceptService) { }

  ngOnInit() {
    if (!this.authService.isLoggedIn) {
        this.router.navigate(['/login'])
    }
    this.receptService.getReceptek()
      .subscribe(data => {
         this.dataSource = new MatTableDataSource<Recept>(data);
      } , error => console.log(error))






  }



}
