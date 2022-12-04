import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Course } from '../../model/course';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.scss']
})
export class CoursesListComponent implements OnInit {

  // Carregando informacos do @Input para obter os cursos
  @Input() courses: Course[] = [];

  // Emitindo o evento @Output para o onAdd()
  @Output() add = new EventEmitter(false);

  readonly displayedColumns = ['_id', 'name', 'category', 'actions'];

  constructor( ) { }

  ngOnInit(): void {
  }

  onAdd() {
    this.add.emit(true);
  }

}
