import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Course } from './../../model/course';

import { Lesson } from '../../model/lesson';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
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
    private route: ActivatedRoute
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
        lessons: this.formBuilder.array(this.retrieveLessons(course))
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
      name: [lesson.name], 
      youtubeUrl: [lesson.youtubeUrl]
    });
  }

  // Metodo de retorno das aulas
  // Tipagem UntypedFormArray para utilizar  no formArray quando não possuir tipagem
  getLessonsFormArray() {
    return (<UntypedFormArray>this.formularioCouseForm.get('lessons')).controls;
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

  // metodo para retorno de erro aos campos obrigatorios ou que contenham validacao
  getErrorMessage(fieldName: string) {
    // Obtem o campo repassado
    const field = this.formularioCouseForm.get(fieldName);

    // Validacao de campo obrigatorio
    if (field?.hasError('required')) {
      return 'esse campo obrigatório'
    }

    // Validacao de caracteres mínimos
    if (field?.hasError('minlength')) {
      // Caso exista erro acessa a propriedade minLength para obter o tamenho minimo de caracteres, 
      // senão retorna o valor padrão de 5
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres`
    }

    // Validacao de caracteres máximos
    if (field?.hasError('maxlength')) {
      // Caso exista erro acessa a propriedade maxLength para obter o tamenho máximo de caracteres
      // senão retorna o valor padrão de 100
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 200;
      return `Tamanho máximo excedido de ${requiredLength} caracteres`
    }


    return 'campo inválido'
  }

}



