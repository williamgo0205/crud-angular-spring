import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { Course } from '../model/course';
import { CoursesService } from './../services/courses.service';

@Component({
  selector: 'app-coursers',
  templateUrl: './coursers.component.html',
  styleUrls: ['./coursers.component.scss']
})
export class CoursersComponent implements OnInit {

  // coursesService: CoursesService;

  // Por conta do modo strict do Angular é necessário inicializar a variável
  // Nesse caso "Course", para isso, colocamos a inicializacao no "constructor"
  // Criando com o nome courses$ pois o Angular transforma a variável em um observable
  courses$: Observable<Course[]>; 

  // Se for utilizar o array será necessario fazer um subscribe para transformar nesse array
  // Aqui nesse ponto estamos utilizando o Observable para o courses
  // courses: Course[] = []; 

  displayedColumns = ['name',  'category'];

  constructor(private coursesService: CoursesService) { 
    // this.courses = [];
    // this.coursesService = new CoursesService();
    // this.coursesService.list().subscribe(courses => { this.courses = courses });
    this.courses$ = this.coursesService.list();
  }

  ngOnInit(): void {    
  }

}
