import { Location, NgIf, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { FormUtilsService } from '../../../shared/form/form-utils.service';
import { Lesson } from '../../model/lesson';
import { CoursesService } from '../../services/courses.service';
import { Course } from './../../model/course';
import { MatIcon } from '@angular/material/icon';
import { MatIconButton, MatButton } from '@angular/material/button';
import { MatOption } from '@angular/material/core';
import { MatSelect } from '@angular/material/select';
import { MatInput } from '@angular/material/input';
import { MatFormField, MatHint, MatError, MatLabel, MatPrefix } from '@angular/material/form-field';
import { MatToolbar } from '@angular/material/toolbar';
import { MatCard, MatCardContent, MatCardActions } from '@angular/material/card';

@Component({
    selector: 'app-course-form',
    templateUrl: './course-form.component.html',
    styleUrls: ['./course-form.component.scss'],
    standalone: true,
    imports: [MatCard, MatToolbar, MatCardContent, ReactiveFormsModule, MatFormField, MatInput, MatHint, NgIf, MatError, MatLabel, MatSelect, MatOption, MatIconButton, MatIcon, NgFor, MatPrefix, MatCardActions, MatButton]
})
export class CourseFormComponent implements OnInit {

  // // Exemplo:
  // // new FormControl('', {nonNullable: true}) - inicializacao do formulário pelo formBuilder
  // // nao permitindo que o valor seja 'nulo' (null)
  
  formularioCouseForm!: FormGroup; // formularioCouseForm! - o "!" permite que declare o construtor do formGroup em outro lugar, por exemplo no ngOnInit

  constructor(
    // Ao invés de utilizar o FormBuilder, pode ser utilizada a nova classe (a partir do Angular 14), NonNullableFormBuilder
    // que classifica todos os campos do formulario como nao nulos, ou seja, é o mesmo que ao iniciar cada atributo do formulario
    // da seguinte maneira: name: new FormControl('', {nonNullable: true}),
    private formBuilder: NonNullableFormBuilder,
    // private formBuilder: FormBuilder,

    private coursesService: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) { }

  ngOnInit(): void {
    // Atualiza a constante com o course que esta vindo do resolver atraves da rota (seja edicao ou inclusao)
    const course: Course = this.route.snapshot.data['course'];
    
    this.formularioCouseForm = this.formBuilder.group({
        // Validator faz validacoes no formulario como nos exemplos abaixo para os campos name e category
        _id: [course._id],
        name: [course.name, [Validators.required, Validators.minLength(5), Validators.maxLength(200)]],
        category: [course.category, [Validators.required]],
        // FormArray de lessons é obtido através do formBuilder
        lessons: this.formBuilder.array(this.retrieveLessons(course), Validators.required)
      });
  }

  // Criação do array para obter as Aulas (retrieveLessons) 
  private retrieveLessons(course: Course) {
    const lessons = [];
    // Verifica se existem lições
    if (course?.lessons) {
      // ForEach abaixo adiciona cada aula (lesson) do curso
      course.lessons.forEach( lesson => 
        lessons.push(this.createLesson(lesson))
      );
    } else { // Verifica se o array de lições está vazio e cria uma nossa lesson vazia
      lessons.push(this.createLesson());
    }

    return lessons;
  }

  // Metodo privado que será responsável por criar um grupo de aulas (Lessons)
  private createLesson(lesson: Lesson = {id: '', name: '', youtubeUrl: ''}) {
    return this.formBuilder.group({
      id: [lesson.id], 
      name: [lesson.name, [Validators.required, Validators.minLength(5), Validators.maxLength(200)]], 
      youtubeUrl: [lesson.youtubeUrl, [Validators.required, Validators.minLength(10), Validators.maxLength(11)]]
    });
  }

  // Metodo de retorno das aulas
  // Tipagem UntypedFormArray para utilizar  no formArray quando não possuir tipagem
  getLessonsFormArray() {
    return (<UntypedFormArray>this.formularioCouseForm.get('lessons')).controls;
  }

  // Adicionando uma nova aula (Lesson)
  addNewLesson() {
    const lessons = this.formularioCouseForm.get('lessons') as UntypedFormArray;
    lessons.push(this.createLesson());
  }

  // Remove uma aula (Lesson)
  removeLesson(index: number) {
    const lessons = this.formularioCouseForm.get('lessons') as UntypedFormArray;
    lessons.removeAt(index);
  }


  onSubmit() {
    // Caso o form esteja válido salva as informações
    if(this.formularioCouseForm.valid) {
      // Repassa os dados do formulario para o metodo save da classe de servico
      this.coursesService.save(this.formularioCouseForm.value)
        .subscribe(
          // Caso sucesso ao salvar os dados
          result => this.onSuccess(),
          // Caso erro ao salvar os dados
          error => this.onError()
        );
    } else {
      //Faz a validação de todos os campos do formulario caso ele não esteja válido
      this.formUtils.validateAllFormFields(this.formularioCouseForm);
    }

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