import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetrieveAllDeliverymanComponent } from './retrieve-all-deliveryman.component';

describe('RetrieveAllDeliverymanComponent', () => {
  let component: RetrieveAllDeliverymanComponent;
  let fixture: ComponentFixture<RetrieveAllDeliverymanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetrieveAllDeliverymanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetrieveAllDeliverymanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
