import {Component, inject, OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BranchService } from '../../Service/branch.service'; // Update path as necessary
import { ActivatedRoute, Router } from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-add-edit-branch',
  templateUrl: './add-edit-branch.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./add-edit-branch.component.css']
})
export class AddEditBranchComponent implements OnInit {
  branchForm: FormGroup;
  isEditing: boolean = false;
  id: number | undefined = undefined;
  branchId: number | undefined = undefined;
  toastr = inject(ToastrService);

  constructor(
    private branchService: BranchService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.branchForm = new FormGroup({
      branchId: new FormControl(this.branchId),
      branchName: new FormControl('', Validators.required),
      branchLocation: new FormControl('', Validators.required),
      openingTime: new FormControl('', Validators.required),
      closingTime: new FormControl('', Validators.required),
      contact: new FormControl('', Validators.required),
      numberOfEmployees: new FormControl('', Validators.required),
    });
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      if (this.id) {
        this.isEditing = true;
        this.branchService.getBranchById(this.id).subscribe(branch => {
          this.branchForm.patchValue(branch.data);
          this.branchId = branch.data.id;
        });
      }
    });
  }

  onSubmit() {
    debugger;
    if (this.branchForm.valid) {
      const action = this.isEditing ? 'updateBranch' : 'addBranch';
      this.branchService[action](this.branchForm.value).subscribe({
        next: (response) => {
          this.toastr.success(`${this.isEditing ? 'Updated' : 'Added'} branch:`);
          this.router.navigate(['/branch']);
        },
        error: (error) => this.toastr.error(`Error ${this.isEditing ? 'updating' : 'adding'} branch:`, error)
      });
    }else{
      console.log('error submit!!');
    }
  }
}
