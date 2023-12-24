import { CoursesService } from '../../services/courses.service';

import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursersComponent } from './courses.component';

describe('CoursersComponent - Unit Tests', () => {
  let component: CoursersComponent;

  const coursesServiceMock = {
    list: jest.fn()
  } as unknown as CoursesService;

  const matDialogMock = {} as unknown as MatDialog;

  const routerMock = {} as unknown as Router;

  const activatedRoutMock = {} as unknown as ActivatedRoute;

  const matSnackBarMock = {} as unknown as MatSnackBar;

  beforeEach(() => {
    component = new CoursersComponent(coursesServiceMock, matDialogMock, routerMock, activatedRoutMock, matSnackBarMock);
    jest.clearAllMocks();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
});
