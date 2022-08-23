import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventListClientComponent } from './event-list-client.component';

describe('EventListClientComponent', () => {
  let component: EventListClientComponent;
  let fixture: ComponentFixture<EventListClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventListClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventListClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
