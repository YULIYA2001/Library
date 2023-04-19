import {Component, OnInit} from '@angular/core';
import {Book} from "../../models/book";
import {BookService} from "../../services/book.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {Genre} from "../../models/genre";
import {GenreService} from "../../services/genre.service";
import {AuthorService} from "../../services/author.service";
import {Author} from "../../models/author";
import {Person} from "../../models/person";
import { PersonService } from 'src/app/services/person.service';

@Component({
  selector: 'app-home',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})


export class HomePageComponent implements OnInit {
  public books: Book[] | undefined;
  public genres: Genre[] | undefined;
  public authors: Author[] | undefined;
  public people: Person[] | undefined;
  public editBook: Book | null | undefined;
  public editBook2: Book | null | undefined;
  public deleteBook: Book | null | undefined;


  constructor(private bookService: BookService, private genreService: GenreService,
              private authorService: AuthorService, private personService: PersonService) {
  }

  ngOnInit() {
    this.getBooks();
  }

  public getBooks(): void {
    this.bookService.getBooks().subscribe(
      (response: Book[]) => {
        this.books = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  // @ts-ignore
  public getGenres(): void {
    this.genreService.getGenres().subscribe(
      (response: Genre[]) => {
        this.genres = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getAuthors(): void {
    this.authorService.getAuthors().subscribe(
      (response: Author[]) => {
        this.authors = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getPersonAll(): void {
    this.personService.getPersonAll().subscribe(
      (response: Person[]) => {
        this.people = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddBook(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-book-form').click();

    let person = null;
    let title: string = addForm.value.title;
    let language: string = addForm.value.language;
    let genre: Genre = addForm.value.genre;
    let authors: Author[] = addForm.value.authors;

    let book: Book = new Book(title, language, person, genre, authors);

    this.bookService.addBook(book).subscribe(
      (response: Book) => {
        console.log(response);
        this.getBooks();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateBook(id: number, personId: number | undefined): void {

    this.bookService.updateBook(id, personId!).subscribe(
      (response: Book) => {
        console.log(response);
        this.getBooks();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteBook(bookId: number | undefined): void {
    if (bookId !== undefined) {
      this.bookService.deleteBook(bookId).subscribe(
        (response: void) => {
          console.log(response);
          this.getBooks();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

  public searchBooks(key: string): void {
    console.log(key);
    const results: Book[] = [];

    // @ts-ignore
    for (const book of this.books) {
      if (book.title.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(book);
      }
      else if (book.language.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(book);
      }
      else if (book.genre.name.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(book);
      }
      else {
        for (let author of book.authors) {
          if (author.name.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
            results.push(book);
            break;
          }
        }
      }
    }
    this.books = results;
    if (results.length === 0 || !key) {
      this.getBooks();
    }
  }

  public onOpenModal(book: Book | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');
    if (mode === 'add') {
      this.getAuthors();
      this.getGenres();
      button.setAttribute('data-bs-target', '#addBookModal');
    }
    if (mode === 'edit') {
      this.getPersonAll();
      this.editBook = book;
      button.setAttribute('data-bs-target', '#editBookModal');
    }
    if (mode === '-edit') {
      this.editBook2 = book;
      button.setAttribute('data-bs-target', '#-editBookModal');
    }
    if (mode === 'delete') {
      this.deleteBook = book;
      button.setAttribute('data-bs-target', '#deleteBookModal');
    }
    // @ts-ignore
    container.appendChild(button);
    button.click();
  }
}



