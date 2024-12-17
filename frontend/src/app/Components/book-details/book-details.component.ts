// book-details.component.ts
import {Component, ElementRef, inject, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../../Service/book.service';
import {ApiResponseModel, Book, getBook} from '../../Model/Interface/Book';
import {NgForOf, NgIf} from '@angular/common';
import * as bootstrap from 'bootstrap';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../Service/auth.service';


@Component({
  selector: 'app-book-details',
  imports: [
    NgIf,
    NgForOf,
    ReactiveFormsModule
  ],
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {
  @ViewChild('reviewModal') reviewModal!: ElementRef<HTMLDivElement>;
book : getBook | null  = null;
likeForm : FormGroup;
addReviewForm: FormGroup;
  rating = 0;// Default rating value
  stars = [1, 2, 3, 4, 5]; // Array for ngFor to create 5 stars
 authService : AuthService = inject(AuthService);

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
  ) {
    this.likeForm = new FormGroup({
      reviewAndRatingId : new FormControl('', Validators.required),
    })
    this.addReviewForm = new FormGroup({
      bookId : new FormControl(this.book?.bookId, Validators.required),
      rating : new FormControl(this.rating, Validators.required),
      review : new FormControl('', Validators.required),
    })
  }


  openModal(): void {
    const modal = new bootstrap.Modal(this.reviewModal.nativeElement);
    modal.show();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const bookId = +params['id']; // Plus sign to convert the parameter string to a number
      if (bookId) {
        this.bookService.getBookById(bookId).subscribe({
          next: (response: ApiResponseModel) => {
            this.book = response.data;
          },
          error: (error : any) => {
            console.error('Error fetching book details:', error);
          }
        });
      }
    });
  }
  setRating(rating: number): void {
    console.log('Setting rating:', rating);
    this.rating = rating;
    // Here you can also add any logic to send the rating to the backend
  }
  onLikeDislike(actionType : 'like' | 'dislike', reviewAndRatingId: number): void {
    debugger;
    this.likeForm.patchValue({
      reviewAndRatingId: reviewAndRatingId
    });
    if (this.likeForm.valid) {
      const action = actionType === 'like' ? 'toggleLike' : 'toggleDislike';
      this.bookService[action](this.likeForm.value).subscribe({
        next: (response) => {
          // Handle successful like/dislike
          console.log(`${actionType} successful`, response);
          this.ngOnInit()
        },
        error: (error) => {
          // Handle error
          console.error(`Error during ${actionType}`, error);
        }
      })
    }else{
      console.log('form not valid');
    }
  }
  onAddReview(bookId : number): void {
    debugger;
    this.addReviewForm.patchValue({
      bookId: bookId
    });
    this.addReviewForm.patchValue({
      rating : this.rating
    });
    if (this.addReviewForm.valid) {
      this.bookService.addReview(this.addReviewForm.value).subscribe({
        next: (response) => {
          this.ngOnInit()
        },
        error: (error: any ) => {
          console.error(`Error adding review: ${error}`);
        }
      })
    }else{
      console.log('form not valid');
    }
  }
}
