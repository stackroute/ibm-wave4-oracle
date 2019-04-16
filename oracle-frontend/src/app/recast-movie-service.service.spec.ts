import { TestBed } from '@angular/core/testing';

import { RecastMovieServiceService } from './recast-movie-service.service';

describe('RecastMovieServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecastMovieServiceService = TestBed.get(RecastMovieServiceService);
    expect(service).toBeTruthy();
  });
});
