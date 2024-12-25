// src/app/Service/branch.service.ts
import {inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponseModel } from '../Model/Interface/Book';

@Injectable({
  providedIn: 'root'
})
export class BranchService {
  private BranchUrl: string = 'http://localhost:9090/api/branch';
  private http: HttpClient = inject(HttpClient);

  constructor() { }

  getAllBranches(): Observable<ApiResponseModel> {
    return this.http.get<ApiResponseModel>(`${this.BranchUrl}/getAllBranches`);
  }

  deleteBranchById(branchId: number): Observable<any> {
    return this.http.delete(`${this.BranchUrl}/deleteBranch/${branchId}`);
  }
  addBranch(formValue: any): Observable<null> {
    debugger;
    return this.http.post<null>(`${this.BranchUrl}/addBranch`, formValue);
  }
  updateBranch(formValue: any): Observable<null> {
    return this.http.put<null>(`${this.BranchUrl}/updateBranchDetails`, formValue);
  }

  getBranchById(branchId: number) {
    return this.http.get<ApiResponseModel>(`${this.BranchUrl}/getBranchById/${branchId}`);
  }
}
