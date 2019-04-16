import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TourismBotComponent } from './tourism-bot.component';

describe('TourismBotComponent', () => {
  let component: TourismBotComponent;
  let fixture: ComponentFixture<TourismBotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TourismBotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TourismBotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
