import {Person} from "./person";
import {Genre} from "./genre";
import {Author} from "./author";

// export interface Book {
//   id: number;
//   title: string;
//   language: string;
//   person: Person;
//   genre: Genre;
//   authors: Author[];
// }

export class Book {
  id!: number;
  title!: string;
  language!: string;
  person!: Person | null;
  genre!: Genre;
  authors!: Author[];


  constructor(title: string, language: string, person: Person | null, genre: Genre, authors: Author[]) {
    this.title = title;
    this.language = language;
    this.person = person;
    this.genre = genre;
    this.authors = authors;
  }
}
