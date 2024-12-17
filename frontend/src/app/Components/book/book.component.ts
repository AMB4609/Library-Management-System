import {Component, inject, OnInit} from '@angular/core';
import {ApiResponseModel, Book} from '../../Model/Interface/Book'; // Update path as necessary
import { BookService } from '../../Service/book.service';
import {NgForOf, NgIf} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../Service/auth.service'; // Update path as necessary

@Component({
  selector: 'app-book-list',
  templateUrl: './book.component.html',
  imports: [
    NgForOf,
    RouterLink,
    NgIf
  ]
})
export class BookComponent implements OnInit {
  books: Book[] = [];
  book: Book | undefined;
  bookService : BookService = inject(BookService);
 router : Router = inject(Router);
 authService : AuthService = inject(AuthService);

  ngOnInit() {
    this.getAllBooks();
  }

  getAllBooks(): void {
    this.bookService.getAllBooks().subscribe((res: ApiResponseModel) =>{
      this.books = res.data;
    });
  }

  editBook(bookId: number) {
    this.router.navigate(['/edit/', bookId]);
  }


  deleteBookById(bookId: number) {
    this.bookService.deleteBookById(bookId).subscribe({
      next: (res : any) => {
        this.books = res;
        this.getAllBooks();
      },
      error: (err: any) => {
        console.error('Error deleting book:', err);
      }
    })
  }

  getBookById(bookId: number) {
    this.router.navigate(['/books/' + bookId]);
  }
}
