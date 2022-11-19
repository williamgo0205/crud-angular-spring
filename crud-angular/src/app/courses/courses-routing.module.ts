import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseFormComponent } from './course-form/course-form.component';

import { CoursersComponent } from './courses/courses.component';

const routes: Routes = [
  { path: '', component: CoursersComponent },
  { path: 'new', component: CourseFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CoursesRoutingModule {}
