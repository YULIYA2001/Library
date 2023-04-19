import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {Book} from "../models/book";


@Injectable({
  providedIn: 'root'
})
export class BookService {

  private apiServiceUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {}


  public getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiServiceUrl}/book/all`);
  }

  public addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(`${this.apiServiceUrl}/book/add`, book);
  }

  public updateBook(id: number, personId: number | undefined): Observable<Book> {
    return this.http.put<Book>(`${this.apiServiceUrl}/book/update/${id}`, personId);
  }

  public deleteBook(bookId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServiceUrl}/book/delete/${bookId}`);
  }
}
