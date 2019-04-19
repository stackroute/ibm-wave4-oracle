import { TestBed } from '@angular/core/testing';

import { InstantMessagingService } from './instant-messaging.service';

describe('InstantMessagingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InstantMessagingService = TestBed.get(InstantMessagingService);
    expect(service).toBeTruthy();
  });
});
