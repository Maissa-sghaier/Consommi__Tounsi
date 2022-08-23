import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliverymanInterfaceComponent } from './deliveryman-interface.component';

describe('DeliverymanInterfaceComponent', () => {
  let component: DeliverymanInterfaceComponent;
  let fixture: ComponentFixture<DeliverymanInterfaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliverymanInterfaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliverymanInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
