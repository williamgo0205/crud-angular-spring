import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

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
  courses$: Observable<Course[]> | null = null; 

  // Se for utilizar o array será necessario fazer um subscribe para transformar nesse array
  // Aqui nesse ponto estamos utilizando o Observable para o courses
  // courses: Course[] = []; 

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) { 
    this.refresh();
  }

  // Funcao responsavel por fazer o refresh da pagina executando 0o mesmo codigo do construtor
  refresh() {
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

  onEdit(course: Course) {
    // adicionando a rota desejada ficando: http://localhost:4200/courses/edit/{id_course}
    this.router.navigate(['edit', course._id], {relativeTo: this.route});
  }

  onDelete(course: Course) {
    // adicionando chamada para a remocao do curso na service
    this.coursesService.delete(course._id)
      .subscribe(
        () => {      
          // Refresh nos dados da pagina
          this.refresh();

          // Adicionando um popup de curso removido com sucesso com botao "X" de fechar
          this.snackBar.open('Curso removido com sucesso!!!', 'X', {
            // configura a duracao de exibicao do snackbar para 5 segundos (5000 ml)
            // posicionamento top (acima) e centralizado
            duration: 5000,
            verticalPosition: 'top',
            horizontalPosition: 'center'
          });
        },
        // Tratamento de erro ao remover o curso
        () => this.onError('Erro ao tentar remover o curso');
      }
  }

}
