// book-details.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../../Service/book.service';
import { Book } from '../../Model/Interface/Book';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-book-details',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './book-details.component.html'
})
export class BookDetailsComponent implements OnInit {
  book: Book | undefined;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const bookId = +params['id']; // Plus sign to convert the parameter string to a number
      if (bookId) {
        this.bookService.getBookById(bookId).subscribe({
          next: (book) => this.book = book,
          error: (err) => console.error('Failed to get the book:', err)
        });
      }
    });
  }
}
