import { TestBed } from '@angular/core/testing';

import { ChatHistoryService } from './chat-history.service';

describe('ChatHistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ChatHistoryService = TestBed.get(ChatHistoryService);
    expect(service).toBeTruthy();
  });
});
