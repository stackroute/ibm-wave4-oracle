import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecastMovieComponent } from './recast-movie.component';

describe('RecastMovieComponent', () => {
  let component: RecastMovieComponent;
  let fixture: ComponentFixture<RecastMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecastMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecastMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
