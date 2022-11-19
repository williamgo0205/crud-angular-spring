import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
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

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) { 
    // this.courses = [];
    // this.coursesService = new CoursesService();
    // this.coursesService.list().subscribe(courses => { this.courses = courses });
    this.courses$ = this.coursesService.list()
    .pipe(
      // Tratamento de erro atraves do catchError que retorna um observable
      catchError(error => {
        console.log(error),
        this.onError('Erro ao carregar a página de cursos')
        return of([])
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  ngOnInit(): void {    
  }

  onAdd() {
    // this.route (ActivatedRoute), redireciona para a rota ativa, nesse caso http://localhost:4200/courses
    // adicionando a rota desejada ficando: http://localhost:4200/courses/new
    this.router.navigate(['new'], {relativeTo: this.route});
  }

}
