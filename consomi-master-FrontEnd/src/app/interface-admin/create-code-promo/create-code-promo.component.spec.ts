import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCodePromoComponent } from './create-code-promo.component';

describe('CreateCodePromoComponent', () => {
  let component: CreateCodePromoComponent;
  let fixture: ComponentFixture<CreateCodePromoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateCodePromoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCodePromoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
