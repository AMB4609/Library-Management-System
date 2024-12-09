
export interface Book {
  bookId: number;
  bookName: string;
  bookDescription: string;
  booksAvailable: number;
  releaseDate: string;
  isbn: string | null;
  isAvailable: boolean;
  author: Author;
  publisher: string;
  category: Category;
  reviews: Review[];
  averageRating: number;
}

export interface Author {
  authorId: number;
  authorName: string;
  authorSurname: string;
  authorEmail: string;
  biography: string;
}

export interface Category {
  categoryId: number;
  categoryName: string;
  categoryDescription: string;
}

export interface Review {
  reviewAndRatingId: number;
  user: User;
  rating: number;
  review: string;
  reviewDate: string;
  likeCount: number;
  dislikeCount: number;
  likedUsers: User[];
  dislikedUsers: User[];
}

export interface User {
  id: number;
  name: string;
  email: string;
  phone: string;
  address: string;
}
