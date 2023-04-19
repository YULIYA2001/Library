import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {Author} from "../models/author";


@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private apiServiceUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {}


  public getAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(`${this.apiServiceUrl}/author/all`);
  }

  public addAuthor(author: Author): Observable<Author> {
    return this.http.post<Author>(`${this.apiServiceUrl}/author/add`, author);
  }

  public updateAuthor(id: number, info: string): Observable<Author> {
    return this.http.put<Author>(`${this.apiServiceUrl}/author/update/${id}`, info);
  }
}
