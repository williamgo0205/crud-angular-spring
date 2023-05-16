import { CoursesService } from '../../services/courses.service';

import { MatLegacyDialog as MatDialog } from '@angular/material/legacy-dialog';
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
