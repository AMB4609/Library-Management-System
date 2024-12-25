// book-details.component.ts
import {Component, ElementRef, inject, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../../Service/book.service';
import {ApiResponseModel, getBook} from '../../Model/Interface/Book';
import {NgForOf, NgIf} from '@angular/common';
import * as bootstrap from 'bootstrap';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../Service/auth.service';
import {ToastrService} from 'ngx-toastr';


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
  isEditing = false;
  book : getBook | null  = null;
  likeForm : FormGroup;
  addReviewForm: FormGroup;
  rating = 0;// Default rating value
  stars = [1, 2, 3, 4, 5]; // Array for ngFor to create 5 stars
  authService : AuthService = inject(AuthService);
  reviewAndRatingId = 0;
  modalInstance: bootstrap.Modal | null = null;
  toastr = inject(ToastrService);

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
  ) {
    this.likeForm = new FormGroup({
      reviewAndRatingId : new FormControl('', Validators.required),
    })
    this.addReviewForm = new FormGroup({
      reviewAndRatingId: new FormControl(''),
      bookId : new FormControl(this.book?.bookId, Validators.required),
      rating : new FormControl('', Validators.required),
      review : new FormControl('', Validators.required),
    })
  }


  openModal(review?: any): void {
    debugger;
    if (review) {
      // Populate the form with existing review data
      this.addReviewForm.patchValue({
        reviewAndRatingId: review.reviewAndRatingId,
        bookId: this.book?.bookId,
        rating: review.rating,
        review: review.review,

      });
      this.rating = review.rating;
      this.isEditing = true;
    } else {
      // Reset the form for adding a new review
      this.addReviewForm.reset({
        reviewAndRatingId : 0,
        bookId: this.book?.bookId,
        rating : 0,
        review: '',
      });
      this.isEditing = false;
    }

     this.modalInstance = new bootstrap.Modal(this.reviewModal.nativeElement);
    this.modalInstance.show();
  }


  ngOnInit(): void {
    debugger;
    this.route.params.subscribe(params => {
      const bookId = +params['id']; // Plus sign to convert the parameter string to a number
      if (bookId) {
        this.bookService.getBookById(bookId).subscribe({
          next: (response: ApiResponseModel) => {
            (response);
            this.book = response.data;
          },
          error: (error : any) => {
            this.toastr.error('Error fetching book details:', error);
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
    this.likeForm.patchValue({
      reviewAndRatingId: reviewAndRatingId
    });
    if (this.likeForm.valid) {
      const action = actionType === 'like' ? 'toggleLike' : 'toggleDislike';
      this.bookService[action](this.likeForm.value).subscribe({
        next: (response) => {
          if(actionType === 'like') {
            this.toastr.success(`${actionType} successful`);
          }else{
            this.toastr.error(`${actionType} successful`);
          }
          this.ngOnInit()
        },
        error: (error) => {
          // Handle error
          this.toastr.error(`Error during ${actionType}`);
        }
      })
    }else{
      this.toastr.error('form not valid');
    }
  }
  onDeleteReview(reviewAndRatingId: number): void {
    this.bookService.deleteReview(reviewAndRatingId).subscribe({
      next: (response) => {
        this.toastr.success('Review was deleted successfully.');
        this.ngOnInit()
      },
      error: (error) => {
        this.toastr.error(`Error during ${reviewAndRatingId}`, error);
      }
    })
  }
  onAddReview(bookId : number): void {
    console.log(this.addReviewForm.value)
    if(this.isEditing){
      this.addReviewForm.patchValue({
        bookId: bookId
      });
      this.addReviewForm.patchValue({
        rating : this.rating
      });
      if (this.addReviewForm.valid) {
        this.bookService.updateReview(this.addReviewForm.value).subscribe({
          next: (response) => {
            this.ngOnInit()
            if (this.modalInstance) {
              this.modalInstance.hide();
            }
          },
          error: (error: any ) => {
            this.toastr.error(`Error updating review: ${error}`);
          }
        })
      }else{
        this.toastr.error('form not valid');
      }
    }else{
      this.addReviewForm.patchValue({
        rating : this.rating
      });
      if (this.addReviewForm.valid) {
        this.bookService.addReview(this.addReviewForm.value).subscribe({
          next: (response) => {
            this.ngOnInit()
            if (this.modalInstance) {
              this.modalInstance.hide();
            }
          },
          error: (error: any ) => {
            this.toastr.error(`Error adding review: ${error}`);
          }
        })
      }else{
        this.toastr.error('form not valid');
      }
    }
  }
}
