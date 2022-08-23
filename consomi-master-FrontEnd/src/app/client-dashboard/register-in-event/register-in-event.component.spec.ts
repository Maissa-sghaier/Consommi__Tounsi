import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterInEventComponent } from './register-in-event.component';

describe('RegisterInEventComponent', () => {
  let component: RegisterInEventComponent;
  let fixture: ComponentFixture<RegisterInEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterInEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterInEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
