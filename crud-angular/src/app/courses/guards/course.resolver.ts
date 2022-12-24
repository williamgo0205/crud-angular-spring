import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot, Resolve,
  RouterStateSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Course } from './../model/course';
import { CoursesService } from './../services/courses.service';

@Injectable({
  providedIn: 'root'
})

// Resolver => fica escutando a rota acionada e pode obter os parametros enviados atraves do snapshot (ActivatedRouteSnapshot)
export class CourseResolver implements Resolve<Course> {

  constructor(
    private service: CoursesService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Course> {
    // Verifica se existe paramento na requisicao e se tem o "id" como parametro 
    if (route.params && route.params['id']) {
      // Faz a chamada da service pelo método findById repassando o "id" existente na URI
      return this.service.findById(route.params['id']);
    }
    // Quando nao for edicao mas for um curso novo ele passa tambem pelo resolver e sendo assim é necessario
    // retornar um objeto novo com os dados vazios assim como existe no form.builder do course-form
    return of({_id: '', name: '', category: ''});
  }
}
