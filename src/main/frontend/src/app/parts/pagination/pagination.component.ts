import {Component, Input, OnInit} from '@angular/core';
import {JwtResponse} from "../../response/JwtResponse";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  @Input() currentPage: any;
  currentUser: JwtResponse;

  constructor() {
  }

  ngOnInit() {
  }

  counter(i = 1) {
    return new Array(i);
  }
}
