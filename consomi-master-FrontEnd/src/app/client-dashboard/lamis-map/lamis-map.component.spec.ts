import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LamisMapComponent } from './lamis-map.component';

describe('LamisMapComponent', () => {
  let component: LamisMapComponent;
  let fixture: ComponentFixture<LamisMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LamisMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LamisMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
