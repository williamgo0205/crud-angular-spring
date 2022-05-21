import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';

@Component({
  selector: 'app-coursers',
  templateUrl: './coursers.component.html',
  styleUrls: ['./coursers.component.scss']
})
export class CoursersComponent implements OnInit {

  courses: Course[] = [
    {_id: '1', name: 'Angular',  category: 'front-end'}
  ];
  displayedColumns = ['name',  'category'];

  constructor() { 
    // this.courses = [];
  }

  ngOnInit(): void {
  }

}
