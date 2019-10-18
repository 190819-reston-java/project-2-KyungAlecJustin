import { TestBed } from '@angular/core/testing';

import { SessionUserService } from './session-user.service';

describe('SessionUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SessionUserService = TestBed.get(SessionUserService);
    expect(service).toBeTruthy();
  });
});
