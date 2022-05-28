import { Component, OnInit } from '@angular/core';

import { Course } from '../model/course';
import { CoursesService } from './../services/courses.service';

@Component({
  selector: 'app-coursers',
  templateUrl: './coursers.component.html',
  styleUrls: ['./coursers.component.scss']
})
export class CoursersComponent implements OnInit {

  // coursesService: CoursesService;

  courses: Course[] = []
  displayedColumns = ['name',  'category'];

  constructor(private coursesService: CoursesService) { 
    // this.courses = [];
    // this.coursesService = new CoursesService();
  }

  ngOnInit(): void {
    this.courses = this.coursesService.list();
  }

}
