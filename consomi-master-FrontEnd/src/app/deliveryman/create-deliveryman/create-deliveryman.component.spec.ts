import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDeliverymanComponent } from './create-deliveryman.component';

describe('CreateDeliverymanComponent', () => {
  let component: CreateDeliverymanComponent;
  let fixture: ComponentFixture<CreateDeliverymanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateDeliverymanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateDeliverymanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
