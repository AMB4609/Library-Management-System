import {Component, inject, OnInit} from '@angular/core';
import { Book } from '../../Model/Interface/Book'; // Update path as necessary
import { BookService } from '../../Service/book.service';
import {NgForOf} from '@angular/common';
import {Router, RouterLink} from '@angular/router'; // Update path as necessary

@Component({
  selector: 'app-book-list',
  templateUrl: './book.component.html',
  imports: [
    NgForOf,
    RouterLink
  ]
})
export class BookComponent implements OnInit {
  books: Book[] = [];
  book: Book | undefined;
  bookService : BookService = inject(BookService);
 router : Router = inject(Router);

  ngOnInit() {
    this.getAllBooks();
  }

  getAllBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (res) => {
        this.books = res;
      },
      error: (err) => {
        console.error('Error fetching books:', err);
      }
    });
  }

  getBookById(bookId: number) {
    this.router.navigate(['/book/' + bookId]);
  }
}
