import {Component, OnInit} from '@angular/core';
import {Genre} from "../../models/genre";
import {GenreService} from "../../services/genre.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-genre',
  templateUrl: './genre-page.component.html'
})
export class GenrePageComponent implements OnInit {
  public genres: Genre[] | undefined;
  public editGenre: Genre | null | undefined;
  public deleteGenre: Genre | null | undefined;


  constructor(private genreService: GenreService) {
  }

  ngOnInit() {
    this.getGenres();
  }

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

  public onAddGenre(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-genre-form').click();
    this.genreService.addGenre(addForm.value).subscribe(
      (response: Genre) => {
        console.log(response);
        this.getGenres();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateGenre(genre: Genre): void {
    this.genreService.updateGenre(genre.id, genre.description).subscribe(
      (response: Genre) => {
        console.log(response);
        this.getGenres();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteGenre(genreId: number | undefined): void {
    if (genreId !== undefined) {
      this.genreService.deleteGenre(genreId).subscribe(
        (response: void) => {
          console.log(response);
          this.getGenres();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

  public searchGenres(key: string): void {
    console.log(key);
    const results: Genre[] = [];

    // @ts-ignore
    for (const genre of this.genres) {
      if (genre.name.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(genre);
      }
    }
    this.genres = results;
    if (results.length === 0 || !key) {
      this.getGenres();
    }
  }

  public onOpenModal(genre: Genre | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-bs-target', '#addGenreModal');
    }
    if (mode === 'edit') {
      this.editGenre = genre;
      button.setAttribute('data-bs-target', '#updateGenreModal');
    }
    if (mode === 'show') {
      this.editGenre = genre;
      button.setAttribute('data-bs-target', '#showGenreModal');
    }
    if (mode === 'delete') {
      this.deleteGenre = genre;
      button.setAttribute('data-bs-target', '#deleteGenreModal');
    }
    // @ts-ignore
    container.appendChild(button);
    button.click();
  }

}


