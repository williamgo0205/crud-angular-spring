import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs/operators';

import { Course } from '../model/course';


@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  // Variavel que indica o caminho da API a ser executada usando o asset local
  // private readonly API = '/assets/courses.json';

  // Variavel que indica o caminho da API, nesse caso o banco em memoria H2 obtido o caminho
  // atraves do arquivo de proxy "proxy.conf.js"
  private readonly API = 'api/courses';

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
      //delay(5000),
      // O Pipe ou subscribe pode manipular o objeto obtido
      // O TAP recebe algo e pode fazer uso dessa informacao de algum modo.
      // Nesse caso apenas exibindo um console.log
      tap(courses => console.log(courses))
  
    );
  }

  // Netodo responsavel por salvar um curso
  // Partial<Course> : Aceita o objeto Curso de forma parcial com pelo menos uma informacao
  save(record: Partial<Course>) {
    // Metodo HTTP POST para salvar um curso
    return this.httpClient.post<Course>(this.API, record)
    .pipe(first());
  }
}
