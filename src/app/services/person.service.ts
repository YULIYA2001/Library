import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';
import {Person, Role} from "../models/person";


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private apiServiceUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {}


  public getPersonAll(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.apiServiceUrl}/user/all`);
  }

  public addPerson(person: Person): Observable<Person> {
    return this.http.post<Person>(`${this.apiServiceUrl}/user/add`, person);
  }

  public updatePerson(id: number, role: string): Observable<Person> {
    return this.http.put<Person>(`${this.apiServiceUrl}/user/update/${id}`, role);
  }

  public loginPerson(email: string, password: string): Observable<Person> {
    let person: Person = new Person(email, password, Role.USER);
    return this.http.post<Person>(`${this.apiServiceUrl}/user/login`, person);
  }
}
