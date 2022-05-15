import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursersComponent } from './coursers.component';

describe('CoursersComponent', () => {
  let component: CoursersComponent;
  let fixture: ComponentFixture<CoursersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoursersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoursersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
