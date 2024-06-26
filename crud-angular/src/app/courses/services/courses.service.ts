import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs/operators';

import { Course } from '../model/course';
import { CoursePage } from '../model/course-page';


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

  // httpClient que retorna o metodo "list" da controller "CourseController" do backend
  list(pageNumber = 0, pageSize = 10) {
    // Metodo HttpClient nesse caso um GET para ler os dados vindos da API
    // P.S. - Esse metdod HttpClient retorna um observable
    return this.httpClient.get<CoursePage>(
      // url - Setando a api a ser chamada (nesse caso "api/courses")
      this.API,
      // options - sendo setado com as informacoes do paginator
      { params: { 
          pageNumber: pageNumber, 
          pageSize: pageSize 
        } 
      } 
    )
    .pipe(
      // first ou take faz o servidor finalizar o canal de conexão após a execução do método
      first(),
      // delay para carregar a pagina (5000 milisegundos = 5 segundos)
      //delay(5000),
      // O Pipe ou subscribe pode manipular o objeto obtido
      // O TAP recebe algo e pode fazer uso dessa informacao de algum modo.
      // Nesse caso apenas exibindo um console.log
      tap(courses => console.log("courses: ", courses))
  
    );
  }

  // httpClient que retorna o metodo "findById" da controller "CourseController" do backend
  findById(id: string) {
    // `${this.API}/${id}` => monta a URL (api/courses/{id})
    return this.httpClient.get<Course>(`${this.API}/${id}`);
  }

  // Netodo responsavel por salvar um curso
  // Partial<Course> : Aceita o objeto Curso de forma parcial com pelo menos uma informacao
  save(record: Partial<Course>) {
    // Caso o registro possua ID ele vai atualizar o registro senao ele vai criar
    if (record._id) {
      return this.update(record);
    }
    // Metodo HTTP POST para salvar um curso
    return this.create(record);
  }

  // Netodo privado responsavel por cria um curso
  // Partial<Course> : Aceita o objeto Curso de forma parcial com pelo menos uma informacao
  private create(record: Partial<Course>) {
    // Metodo HTTP POST para salvar um curso
    return this.httpClient.post<Course>(this.API, record)
    .pipe(first());
  }

  // Netodo privado responsavel por alterar um curso
  // Partial<Course> : Aceita o objeto Curso de forma parcial com pelo menos uma informacao
  private update(record: Partial<Course>) {
    // Metodo HTTP PUT para atualizar um curso
    return this.httpClient.put<Course>(`${this.API}/${record._id}`, record)
    .pipe(first());
  }

  // Netodo responsavel por deletar um curso
  // Partial<Course> : Aceita o objeto Curso de forma parcial com pelo menos uma informacao
  delete(id: string) {
    // Metodo HTTP DELETE para deletar um curso
    return this.httpClient.delete(`${this.API}/${id}`)
    .pipe(first());
  }
}
