import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { CourseFormComponent } from './course-form/course-form.component';
import { CoursesListComponent } from './courses-list/courses-list.component';
import { CoursesRoutingModule } from './courses-routing.module';
import { CoursersComponent } from './courses/courses.component';

@NgModule({
  declarations: [
    CoursersComponent,
    CourseFormComponent,
    CoursesListComponent
  ],
  imports: [
    CommonModule,
    CoursesRoutingModule,
    AppMaterialModule,
    SharedModule,
    // ReactiveFormsModule - Fornece o form builder e o form group necesários ao componente  course-form
    ReactiveFormsModule]
})
export class CoursesModule { }
