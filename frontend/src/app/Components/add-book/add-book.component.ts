import { Component } from '@angular/core';
import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';
import { BookService } from '../../Service/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent {
  bookForm: FormGroup;

  constructor(private bookService: BookService) {
    this.bookForm = new FormGroup({
      authorId: new FormControl('', Validators.required),
      publisherName: new FormControl('', Validators.required),
      categoryId: new FormControl('', Validators.required),
      bookName: new FormControl('', Validators.required),
      ISBN: new FormControl('', [Validators.required, Validators.pattern(/^\d{3}$/)]),
      booksAvailable: new FormControl('', [Validators.required, Validators.min(1)]),
      status: new FormControl(true, Validators.required),
      releaseDate: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    if (this.bookForm.valid) {
      // Implementation of what happens when the form is valid
      this.bookService.addBook(this.bookForm.value).subscribe({
        next: (response) => console.log('Book added:', response),
        error: (error) => console.error('Error adding book:', error)
      });
    } else {
      console.error('Form is not valid:', this.bookForm.errors);
    }
  }
}
