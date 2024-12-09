import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Book} from '../Model/Interface/Book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = 'http://localhost:9090/api/book';
  constructor(private http: HttpClient, private router: Router) { }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}/getAllBooks`);
  }

  getBookById(bookId: number | undefined):Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/getBookById/${bookId}`);
  }

  addBook(formValue: any):Observable<null> {
    return this.http.post<null>(`${this.apiUrl}/addBook`, formValue);

  }
}
