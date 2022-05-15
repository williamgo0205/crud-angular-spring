import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CoursersComponent } from './coursers/coursers.component';

const routes: Routes = [
  { path: '', component: CoursersComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CoursesRoutingModule {}
