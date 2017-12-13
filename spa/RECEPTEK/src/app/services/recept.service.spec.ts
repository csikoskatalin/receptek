import { TestBed, inject } from '@angular/core/testing';

import { ReceptService } from './recept.service';

describe('ReceptService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReceptService]
    });
  });

  it('should be created', inject([ReceptService], (service: ReceptService) => {
    expect(service).toBeTruthy();
  }));
});
