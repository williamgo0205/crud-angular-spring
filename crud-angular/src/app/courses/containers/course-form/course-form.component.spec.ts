
import 'jest';

import { Location } from '@angular/common';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { CoursesService } from '../../services/courses.service';
import { CourseFormComponent } from './course-form.component';

describe('CourseFormComponent - unit tests', () => {
  let component: CourseFormComponent;

  const formBuilderMock = {

  } as unknown as NonNullableFormBuilder;

  const coursesServiceMock = {
    save: jest.fn()
  } as unknown as CoursesService;

  const matSnackBarrMock = {

  } as unknown as MatSnackBar;

  const locationMock = {

  } as unknown as Location;

  const activatedRouteMock = {
    snapshot: jest.fn().mockReturnValue({
        data: {
          course: {
            _id: 1,
            name: 'teste',
            category: 'FrontEnd',
            lessons: []
          }
        }
      })
  } as unknown as ActivatedRoute;

  beforeEach(() => {
    component = new CourseFormComponent(formBuilderMock, coursesServiceMock, matSnackBarrMock, locationMock, activatedRouteMock);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // it('should ngOnInit', () => {
  //   component.ngOnInit();

  //   expect(component).toBeTruthy();
  // });
});
