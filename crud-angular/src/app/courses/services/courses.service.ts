import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Course } from '../model/course';


@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(
    // Injecao do Http Client para chamadas AJAX
    // Isso deve ser declarado também no app.module
    private httpClient: HttpClient
    ) { }

  list(): Course[] {
    return [
      { _id: '1', name: 'Angular',  category: 'front-end' }
    ];
  }
}
