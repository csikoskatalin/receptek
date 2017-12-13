import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SajatreceptekComponent } from './sajatreceptek.component';

describe('SajatreceptekComponent', () => {
  let component: SajatreceptekComponent;
  let fixture: ComponentFixture<SajatreceptekComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SajatreceptekComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SajatreceptekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
