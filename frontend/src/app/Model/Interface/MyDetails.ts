import {Branch} from './Branch';


export interface Staff {
  staffId: number;
  branchId: number;
  staffName: string;
  staffPhone: number;
  staffEmail: string;
  staffAddress: string;
  position: string;
}
export interface UpdateStaffDetails {
  staffId: number;
  staffName: string;
  staffPhone: number;
  staffEmail: string;
  staffAddress: string;
  staffPassword: string;
}
export interface UpdateUserDetails {
  userAddress : string;
  userEmail: string;
  userId: number;
  userName: string;
  userPhone: number;
  userPassword: string;
}
export interface UserDetails {
  userAddress : string;
  userEmail: string;
  userId: number;
  userName: string;
  userPhone: number;
}

