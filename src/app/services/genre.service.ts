import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {Genre} from "../models/genre";


@Injectable({
  providedIn: 'root'
})
export class GenreService {

  private apiServiceUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {}


  public getGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(`${this.apiServiceUrl}/genre/all`);
  }

  public addGenre(genre: Genre): Observable<Genre> {
    return this.http.post<Genre>(`${this.apiServiceUrl}/genre/add`, genre);
  }

  public updateGenre(id: number, description: string): Observable<Genre> {
    return this.http.put<Genre>(`${this.apiServiceUrl}/genre/update/${id}`, description);
  }

  public deleteGenre(genreId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServiceUrl}/genre/delete/${genreId}`);
  }
}
