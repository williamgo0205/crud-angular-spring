import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatLegacySnackBar as MatSnackBar } from '@angular/material/legacy-snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Course } from './../../model/course';

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
  
  // formularioCouseForm = this.formBuilder.group({
  //   name: new FormControl('', {nonNullable: true}),
  //   category: new FormControl('', {nonNullable: true})
  // });

  formularioCouseForm = this.formBuilder.group({
    // Validator faz validacoes no formulario como nos exemplos abaixo para os campos name e category
    _id: [''],
    name: ['', [Validators.required, 
      Validators.minLength(5), 
      Validators.maxLength(200)]],
    category: ['', [Validators.required]]
  });

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
  ) { 
    // Criando um grupo dos "cursos" atraves do formBuilder
    // this.formularioCouseForm = this.formBuilder.group({
    //   name: [''],
    //   category: ['']
    // });
  }

  ngOnInit(): void {
    // Atualiza a constante com o course que esta vindo do resolver atraves da rota (seja edicao ou inclusao)
    const course: Course = this.route.snapshot.data['course'];

    // this.formularioCouseForm.setValue => Seta os valores do formulario com os dados obtidos da rota
    this.formularioCouseForm.setValue({
      _id: course._id,
      name: course.name, 
      category: course.category
    });
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



