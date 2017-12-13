import { Component, OnInit } from '@angular/core';
import {Recept} from "../../model/Recept";
import {ReceptService} from "../../services/recept.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-receptdetails',
  templateUrl: './receptdetails.component.html',
  styleUrls: ['./receptdetails.component.css']
})
export class ReceptdetailsComponent implements OnInit {
  recept: Recept = new Recept();
  id: number;


  constructor(private receptService: ReceptService, private route: ActivatedRoute,
              private router: Router  ) {
    this.route.params.subscribe(
      params => this.id = params.id,
      err => console.log(err)
    )
  }

  ngOnInit() {
    this.reload();
  }
  private reload() {
    this.receptService.read(this.id)
      .subscribe(
        recept => this.recept = recept,
        err => console.log(err)
      )
  }

}
