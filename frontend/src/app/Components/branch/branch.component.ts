import { Component, OnInit, inject } from '@angular/core';
import { Branch } from '../../Model/Interface/Branch'; // Update path as necessary
import { ApiResponseModel} from '../../Model/Interface/Book';
import { BranchService } from '../../Service/branch.service';
import { NgForOf, NgIf } from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import { AuthService } from '../../Service/auth.service';
import {ToastrService} from 'ngx-toastr'; // Update path as necessary

@Component({
  selector: 'app-branch-list',
  templateUrl: './branch.component.html',
  imports: [
    NgForOf,
    NgIf,
    RouterLink
  ]
})
export class BranchComponent implements OnInit {
  branches: Branch[] = [];
  branchService : BranchService = inject(BranchService);
  router : Router = inject(Router);
  authService : AuthService = inject(AuthService);
  toastr = inject(ToastrService);

  ngOnInit() {
    this.getAllBranches();
  }

  getAllBranches(): void {
    this.branchService.getAllBranches().subscribe((res: ApiResponseModel) => {
      this.branches = res.data;
      this.toastr.success("Branches successfully retrieved");
    });
  }

  editBranch(branchId: number) {
    this.router.navigate(['/editBranch/', branchId]);
  }

  deleteBranchById(branchId: number) {
    this.branchService.deleteBranchById(branchId).subscribe({
      next: () => {
        this.getAllBranches();
        this.toastr.success('Branch deleted');
      },
      error: (err: any) => {
        this.toastr.error('Error deleting branch:', err);
      }
    })
  }

  viewBranchDetails(branchId: number) {
    this.router.navigate(['/branches/' + branchId]);
  }
}
