import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductFiltreComponent } from './product-filtre.component';

describe('ProductFiltreComponent', () => {
  let component: ProductFiltreComponent;
  let fixture: ComponentFixture<ProductFiltreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductFiltreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductFiltreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
