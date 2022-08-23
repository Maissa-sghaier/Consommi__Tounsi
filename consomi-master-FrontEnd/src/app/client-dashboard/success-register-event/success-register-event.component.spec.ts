import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessRegisterEventComponent } from './success-register-event.component';

describe('SuccessRegisterEventComponent', () => {
  let component: SuccessRegisterEventComponent;
  let fixture: ComponentFixture<SuccessRegisterEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessRegisterEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessRegisterEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
