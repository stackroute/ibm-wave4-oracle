import { TestBed } from '@angular/core/testing';

import { TourismService } from './tourism.service';

describe('TourismService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TourismService = TestBed.get(TourismService);
    expect(service).toBeTruthy();
  });
});
