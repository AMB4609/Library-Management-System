import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditStaffUserDetailsComponent } from './edit-staff-user-details.component';

describe('EditStaffUserDetailsComponent', () => {
  let component: EditStaffUserDetailsComponent;
  let fixture: ComponentFixture<EditStaffUserDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditStaffUserDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditStaffUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
