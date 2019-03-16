import { TestBed } from '@angular/core/testing';

import { Mod1Service } from './mod1.service';

describe('Mod1Service', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: Mod1Service = TestBed.get(Mod1Service);
    expect(service).toBeTruthy();
  });
});
