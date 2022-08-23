import { TestBed } from '@angular/core/testing';

import { Event.ServiceService } from './event.service.service';

describe('Event.ServiceService', () => {
  let service: Event.ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Event.ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
