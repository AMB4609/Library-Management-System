import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {ApiResponseModel, Book} from '../Model/Interface/Book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = 'http://localhost:9090/api/books';
  private reviewUrl = 'http://localhost:9090/api/reviewAndRating';
  constructor(private http: HttpClient, private router: Router) { }

  getAllBooks(): Observable<ApiResponseModel> {
    return this.http.get<ApiResponseModel>(`${this.apiUrl}/getAllBooks`);
  }

  getBookById(bookId: number | undefined):Observable<ApiResponseModel> {
    return this.http.get<ApiResponseModel>(`${this.apiUrl}/getBookById/${bookId}`);
  }

  addBook(formValue: any):Observable<null> {
    debugger;
  return this.http.post<null>(`${this.apiUrl}/addBook`,formValue);

  }
  updateBook(formValue: any):Observable<null> {
    ;
    return this.http.put<null>(`${this.apiUrl}/updateBookDetails`,formValue);
  }

  deleteBookById(bookId: number):Observable<null> {
    return this.http.delete<null>(`${this.apiUrl}/deleteBookById/${bookId}`);
  }
  toggleLike(reviewAndRatingId: number):Observable<boolean> {
    ;
    return this.http.post<boolean>(`${this.reviewUrl}/toggleLikeToReview`, reviewAndRatingId);
  }
  toggleDislike(reviewAndRatingId: number):Observable<boolean> {
    return this.http.post<boolean>(`${this.reviewUrl}/toggleDislikeToReview`, reviewAndRatingId);
  }

  addReview(formValue :any ):Observable<null> {
    ;
    return this.http.post<null>(`${this.reviewUrl}/addReviewAndRating`, formValue);
  }
  updateReview(formValue: any):Observable<null> {
    ;
    return this.http.put<null>(`${this.reviewUrl}/changeReviewAndRating`, formValue);
  }

  deleteReview(reviewAndRatingId: number) {
    ;;
    return this.http.delete<null>(`${this.reviewUrl}/deleteReviewAndRating/${reviewAndRatingId}`);
  }
}
