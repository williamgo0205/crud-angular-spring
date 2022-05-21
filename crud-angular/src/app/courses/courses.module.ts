import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';

import { CoursersComponent } from './coursers/coursers.component';
import { CoursesRoutingModule } from './courses-routing.module';


@NgModule({
  declarations: [
    CoursersComponent
  ],
  imports: [
    CommonModule,
    CoursesRoutingModule,
    MatTableModule
  ]
})
export class CoursesModule { }
