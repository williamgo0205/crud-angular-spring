import { Location } from '@angular/common';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';

import { Course } from '../../model/course';
import { Lesson } from '../../model/lesson';
import { CoursesService } from '../../services/courses.service';
import { CourseFormComponent } from './course-form.component';

const lessonMock: Lesson = {
  id: '1',
  name: 'aula teste',
  youtubeUrl: 'www.teste.com.br'
}

const courseMock: Course = {
  _id: '1',
  name: 'courso teste',
  category: 'FrontEnd',
  lessons: [lessonMock]
};

describe('CourseFormComponent - unit tests', () => {
  let component: CourseFormComponent;

  const formBuilderMock = {
    group: jest.fn().mockReturnValue(courseMock),
    array: jest.fn().mockReturnValue({})
  } as unknown as NonNullableFormBuilder;

  const coursesServiceMock = {
    save: jest.fn()
  } as unknown as CoursesService;

  const matSnackBarrMock = {

  } as unknown as MatSnackBar;

  const locationMock = {

  } as unknown as Location;

  const activatedRouteMock = { } as unknown as ActivatedRoute;

  beforeEach(() => {
    component = new CourseFormComponent(formBuilderMock, coursesServiceMock, matSnackBarrMock, locationMock, activatedRouteMock);
    jest.clearAllMocks();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should ngOnInit with course contain lesson', () => {
    activatedRouteMock.snapshot = {
      data: {
        course: courseMock
      }
    } as unknown as ActivatedRouteSnapshot;

    component.ngOnInit();

    expect(component.formularioCouseForm).toEqual(courseMock);
  });

  it('should ngOnInit with course not contain lesson', () => {
    let courseMockTest = courseMock;
    courseMockTest.lessons = undefined;

    activatedRouteMock.snapshot = {
      data: {
        course: courseMockTest
      }
    } as unknown as ActivatedRouteSnapshot;

    component.ngOnInit();

    expect(component.formularioCouseForm).toEqual(courseMockTest);
  });

});
