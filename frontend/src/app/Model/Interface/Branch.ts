export interface Branch {
  branchId: number;
  branchName: string;
  branchLocation: string;
  getIsOpen: boolean;
}
export interface BranchDetail {
  branchId: number;
  branchName: string;
  branchLocation: string;
  getIsOpen: boolean;
  openingTime: string;
  closingTime: string;
  contact: string;
}
