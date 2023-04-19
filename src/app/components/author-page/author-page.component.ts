import {Component, OnInit} from '@angular/core';
import {Author} from "../../models/author";
import {AuthorService} from "../../services/author.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-author',
  templateUrl: './author-page.component.html'
})
export class AuthorPageComponent implements OnInit {
  public authors: Author[] | undefined;
  public editAuthor: Author | null | undefined;
  // public deleteAuthor: Author | null | undefined;


  constructor(private authorService: AuthorService) {
  }

  ngOnInit() {
    this.getAuthors();
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

  public onAddAuthor(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-author-form').click();
    this.authorService.addAuthor(addForm.value).subscribe(
      (response: Author) => {
        console.log(response);
        this.getAuthors();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateAuthor(author: Author): void {
    this.authorService.updateAuthor(author.id, author.info).subscribe(
      (response: Author) => {
        console.log(response);
        this.getAuthors();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  // public onDeleteAuthor(authorId: number | undefined): void {
  //   if (authorId !== undefined) {
  //     this.authorService.deleteAuthor(authorId).subscribe(
  //       (response: void) => {
  //         console.log(response);
  //         this.getAuthors();
  //       },
  //       (error: HttpErrorResponse) => {
  //         alert(error.message);
  //       }
  //     );
  //   }
  // }

  public searchAuthors(key: string): void {
    console.log(key);
    const results: Author[] = [];

    // @ts-ignore
    for (const author of this.authors) {
      if (author.name.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(author);
      }
    }
    this.authors = results;
    if (results.length === 0 || !key) {
      this.getAuthors();
    }
  }

  public onOpenModal(author: Author | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-bs-target', '#addAuthorModal');
    }
    if (mode === 'edit') {
      this.editAuthor = author;
      button.setAttribute('data-bs-target', '#updateAuthorModal');
    }
    if (mode === 'show') {
      this.editAuthor = author;
      button.setAttribute('data-bs-target', '#showAuthorModal');
    }
    // @ts-ignore
    container.appendChild(button);
    button.click();
  }

}



