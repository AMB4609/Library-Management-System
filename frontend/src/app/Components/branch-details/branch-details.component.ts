import {Component, inject, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BranchService } from '../../Service/branch.service'; // Ensure this path is correct
import {Branch, BranchDetail} from '../../Model/Interface/Branch';
import {NgIf} from '@angular/common';
import {ToastrService} from 'ngx-toastr'; // Ensure this path is correct and the model is defined

@Component({
  selector: 'app-branch-detail',
  templateUrl: './branch-details.component.html',
  imports: [
    NgIf
  ],
  styleUrls: ['./branch-details.component.css']
})
export class BranchDetailComponent implements OnInit {
  branch: BranchDetail | undefined ;
  toastr = inject(ToastrService);

  constructor(
    private branchService: BranchService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const branchId = +params['id'];  // Plus sign to convert the parameter string to a number
      if (branchId) {
        this.branchService.getBranchById(branchId).subscribe({
          next: (response) => {
            this.toastr.success("Branch has been retrieved");
            this.branch = response.data; // Adjust depending on your API response structure
          },
          error: (error: any) => {
            this.toastr.error('Error fetching branch details:', error);
          }
        });
      }
    });
  }
}
