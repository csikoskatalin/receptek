import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {User} from "../../model/User";

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {
  displayedColumns = ['id', 'username', 'role' ];
  dataSource;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    if (!this.authService.isLoggedIn) {
      this.router.navigate(['/login'])
    }
    this.authService.getUsers()
      .subscribe(data => {
        this.dataSource = new MatTableDataSource<User>(data);
      } , error => console.log(error))


  }

  delete(id: number) {
    this.authService.delete(id)
      .subscribe(
        res => {
          this.router.navigate(['/receptek'])
        },
            err => console.log(err)
      );
  }

}
