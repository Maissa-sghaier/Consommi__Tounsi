import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassedeventsComponent } from './passedevents.component';

describe('PassedeventsComponent', () => {
  let component: PassedeventsComponent;
  let fixture: ComponentFixture<PassedeventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassedeventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassedeventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
