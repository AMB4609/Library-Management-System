import {LocalDate} from '@js-joda/core';

export interface Book {
  bookId: number;
  bookName: string;
  bookDescription: string;
  booksAvailable: number;
  releaseDate: string;
  isbn: string | null;
  authorName: string;
  publisher: string;
  categoryId: number;
  categoryName : string;
  isAvailable: boolean;
  averageRating : number;
}
interface user {
  id: number;
  name: string;
  email: string;
  phone: number;
  address: string;
  loanCount: number;
}

// Define the Review and Rating model
interface ReviewAndRating {
  reviewAndRatingId: number;
  user: user;
  rating: number;
  review: string;
  reviewDate: string;
  likeCount: number;
  dislikeCount: number;
  likedUsers: any[]; // Specify more precise type if available
  dislikedUsers: any[]; // Specify more precise type if available
}

// Define the Book model including ReviewAndRating
export interface getBook {
  bookId: number;
  authorId: number;
  publisher: string;
  categoryId: number;
  bookName: string;
  bookDescription: string | null;
  booksAvailable: number;
  releaseDate: string;
  authorName: string;
  categoryName: string;
  averageRating: number;
  isAvailable: boolean;
  reviewAndRating: ReviewAndRating[];
  iSBN: string | null;
}
export interface ApiResponseModel {
  message: string;
  code: number;
  status: boolean;
  data: any;
}

export interface Author {
  authorId: number;
  authorName: string;
  authorSurname: string;
  authorEmail: string;
  biography: string;
}

export interface Category {
  categoryId: number;
  categoryName: string;
  categoryDescription: string;
}

export interface Review {
  reviewAndRatingId: number;
  user: User;
  rating: number;
  review: string;
  reviewDate: string;
  likeCount: number;
  dislikeCount: number;
  likedUsers: User[];
  dislikedUsers: User[];
}

export interface User {
  id: number;
  name: string;
  email: string;
  phone: string;
  address: string;
}
