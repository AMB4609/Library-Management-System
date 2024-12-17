import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';
import { BookService } from '../../Service/book.service';
import { ActivatedRoute, Router } from '@angular/router';

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
  id : number | undefined = undefined;

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.bookForm = new FormGroup({
      bookId: new FormControl(this.route.snapshot.params['id']),
      authorId: new FormControl('',Validators.required),
      authorName: new FormControl('', Validators.required),
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
          this.bookForm.patchValue(book.data);
        });
      }
    });
  }

  onSubmit() {
    if (this.bookForm.valid) {
      debugger;
      const action = this.isEditing ? 'updateBook' : 'addBook';
      this.bookService[action](this.bookForm.value).subscribe({
        next: (response) => {
          console.log(`${this.isEditing ? 'Updated' : 'Added'} book:`, response);
          this.router.navigate(['/books']);
        },
        error: (error) => console.log(`Error ${this.isEditing ? 'updating' : 'adding'} book:`, error)
      });
    }
  }
}
