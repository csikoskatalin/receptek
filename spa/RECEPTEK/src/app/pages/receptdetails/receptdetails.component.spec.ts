import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceptdetailsComponent } from './receptdetails.component';

describe('ReceptdetailsComponent', () => {
  let component: ReceptdetailsComponent;
  let fixture: ComponentFixture<ReceptdetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReceptdetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReceptdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
