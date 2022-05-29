import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs/operators';

import { Course } from '../model/course';


@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  // Variavel que indica o caminho da API a ser executada
  private readonly API = '/assets/acourses.json';

  constructor(
    // Injecao do Http Client para chamadas AJAX
    // Isso deve ser declarado também no app.module
    private httpClient: HttpClient
    ) { }

  list() {
    // Metodo HttpClient nesse caso um GET para ler os dados vindos da API
    // P.S. - Esse metdod HttpClient retorna um observable
    return this.httpClient.get<Course[]>(this.API)
    .pipe(
      // first ou take faz o servidor finalizar o canal de conexão após a execução do método
      first(),
      // delay para carregar a pagina (5000 milisegundos = 5 segundos)
      delay(5000),
      // O Pipe ou subscribe pode manipular o objeto obtido
      // O TAP recebe algo e pode fazer uso dessa informacao de algum modo.
      // Nesse caso apenas exibindo um console.log
      tap(courses => console.log(courses))
  
    );
  }
}
