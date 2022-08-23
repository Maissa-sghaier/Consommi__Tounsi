import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetrieveAllClientsComponent } from './retrieve-all-clients.component';

describe('RetrieveAllClientsComponent', () => {
  let component: RetrieveAllClientsComponent;
  let fixture: ComponentFixture<RetrieveAllClientsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetrieveAllClientsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetrieveAllClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
