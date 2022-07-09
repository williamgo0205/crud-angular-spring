import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  formularioCouseForm: FormGroup ;

  constructor(
    private formBuilder: FormBuilder,
    private coursesService: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location
  ) { 
    // Criando um grupo dos "cursos" atraves do formBuilder
    this.formularioCouseForm = this.formBuilder.group({
      name: [null],
      category: [null]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    // Verifica o valor do formulario = this.formularioCouseForm.value
    console.log(this.formularioCouseForm.value)
    // Repassa os dados do formulario para o metodo save da classe de servico
    this.coursesService.save(this.formularioCouseForm.value)
      .subscribe(
        // Caso sucesso ao salvar os dados
        result => this.onSuccess(),
        // Caso erro ao salvar os dados
        error => this.onError()
      );
  }

  onCancel() {
    console.log('onCancel')
    // Voltando a página anterior = this.location.back()
    this.location.back();
  }

  private onSuccess() {
    // Caso de erro exibe o snackbar informando o erro com a mensagem em uma duracao de 5 segundo (5000 ms)
    this.snackBar.open('Curso Salvo com sucesso!!!', '', {duration: 5000});
    this.onCancel();
  }

  private onError() {
    // Caso de erro exibe o snackbar informando o erro com a mensagem em uma duracao de 5 segundo (5000 ms)
    this.snackBar.open('Erro ao salvar curso', '', {duration: 5000});
  }

}



