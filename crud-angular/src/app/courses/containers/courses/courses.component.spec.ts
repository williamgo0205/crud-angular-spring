import { CoursesService } from '../../services/courses.service';

import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursersComponent } from './courses.component';

describe('CoursersComponent - Unit Tests', () => {
  let component: CoursersComponent;

  // Mocks
  const coursesServiceMock = {} as unknown as CoursesService;

  const matDialogMock = {} as unknown as MatDialog;

  const routerMock = {} as unknown as Router;

  const activatedRoutMock = {} as unknown as ActivatedRoute;

  beforeEach(() => {
    component = new CoursersComponent(coursesServiceMock, matDialogMock, routerMock, activatedRoutMock)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
