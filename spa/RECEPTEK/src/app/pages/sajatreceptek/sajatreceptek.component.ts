import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Recept} from "../../model/Recept";
import {ReceptService} from "../../services/recept.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-sajatreceptek',
  templateUrl: './sajatreceptek.component.html',
  styleUrls: ['./sajatreceptek.component.css']
})
export class SajatreceptekComponent implements OnInit {
  displayedColumns = ['id', 'name', 'status'];
  dataSource;


  receptForm: FormGroup = new FormGroup({
    text: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required])
  });

  constructor(private receptService: ReceptService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    if (!this.authService.isLoggedIn) {
      this.router.navigate(['/login'])
    }

    this.receptService.getReceptek()
      .subscribe(data => {
        let ujdata: Recept[] = [];
        data.filter(d => {
          if ( d.user.id === this.authService.user.id ) {
            ujdata.push(d);
          }
        });
        console.log(ujdata);
        this.dataSource = new MatTableDataSource<Recept>(ujdata);


      } , error => console.log(error))


  }

  get name() {
    return this.receptForm.get('name')
  }

  get text() {
    return this.receptForm.get('text')
  }

  submit() {
    this.receptService.create(new Recept(this.name.value, this.text.value))
      .subscribe(
        res => {
          this.router.navigate(['/receptek'])
        },
        err => console.log(err)
      )
  }



}
