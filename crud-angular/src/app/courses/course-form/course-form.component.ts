import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  formularioCouseForm: FormGroup ;

  constructor(
    private formBuilder: FormBuilder 
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
  
  }

  onCancel() {
  
  }

}



