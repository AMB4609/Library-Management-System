import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffUserDetailsComponent } from './staff-user-details.component';

describe('StaffUserDetailsComponent', () => {
  let component: StaffUserDetailsComponent;
  let fixture: ComponentFixture<StaffUserDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StaffUserDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StaffUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
