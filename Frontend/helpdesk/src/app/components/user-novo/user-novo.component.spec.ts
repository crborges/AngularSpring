import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserNovoComponent } from './user-novo.component';

describe('UserNovoComponent', () => {
  let component: UserNovoComponent;
  let fixture: ComponentFixture<UserNovoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserNovoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserNovoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
