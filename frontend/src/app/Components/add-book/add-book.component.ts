import {Component, inject, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../Service/book.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  bookForm: FormGroup;
  isEditing: boolean = false;
  id: number | undefined;
  toastr = inject(ToastrService);

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.bookForm = new FormGroup({
      authorId: new FormControl('', Validators.required),
      publisherName: new FormControl('', Validators.required),
      categoryId: new FormControl('', Validators.required),
      bookName: new FormControl('', Validators.required),
      ISBN: new FormControl('', Validators.required),
      booksAvailable: new FormControl('', [Validators.required, Validators.min(1)]),
      status: new FormControl(true, Validators.required),
      releaseDate: new FormControl('', Validators.required)
    });
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id']; // + converts string 'id' to a number
      if (this.id) {
        this.isEditing = true;
        this.bookService.getBookById(this.id).subscribe(book => {
          // Include bookId when loading the form for editing
          this.bookForm.patchValue({
            ...book.data,
            bookId: this.id // Ensuring bookId is part of the form data during edits
          });
        });
      }
    });
  }

  onSubmit() {
    console.log(this.bookForm.value);
    if (this.bookForm.valid) {
      if (this.isEditing) {
        // Include the bookId only when updating
        const updatedBookData = {
          ...this.bookForm.value,
          bookId: this.id
        };
        this.bookService.updateBook(updatedBookData).subscribe({
          next: (response) => {
            this.toastr.success('Updated book');
            this.toastr.success('Book successfully added');
            this.router.navigate(['/books']);
          },
          error: (error) => this.toastr.error('Error updating book')
        });
      } else {
        // Add new book without bookId
        this.bookService.addBook(this.bookForm.value).subscribe({
          next: (response) => {
            this.toastr.success('Added new book');
            this.router.navigate(['/books']);
          },
          error: (error) => this.toastr.error('Error adding book')
        });
      }
    }
  }
}
