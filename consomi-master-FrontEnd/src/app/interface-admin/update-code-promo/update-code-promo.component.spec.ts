import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCodePromoComponent } from './update-code-promo.component';

describe('UpdateCodePromoComponent', () => {
  let component: UpdateCodePromoComponent;
  let fixture: ComponentFixture<UpdateCodePromoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCodePromoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCodePromoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
