import {Component, OnInit} from '@angular/core';
import {Person, Role} from "../../models/person";
import {PersonService} from "../../services/person.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-person',
  templateUrl: './person-page.component.html',
  styleUrls: ['./person-page.component.css']
})
export class PersonPageComponent implements OnInit {
  public people: Person[] | undefined;
  public editPerson: Person | null | undefined;
  // public deletePerson: Person | null | undefined;


  constructor(private personService: PersonService) {
  }

  ngOnInit() {
    this.getPeople();
  }

  public getPeople(): void {
    this.personService.getPersonAll().subscribe(
      (response: Person[]) => {
        this.people = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddPerson(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-person-form').click();
    this.personService.addPerson(addForm.value).subscribe(
      (response: Person) => {
        console.log(response);
        this.getPeople();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdatePerson(person: Person): void {
    if (person.role === Role.USER)
      person.role = Role.ADMIN;
    else
      person.role = Role.USER;
    this.personService.updatePerson(person.id, person.role.toString()).subscribe(
      (response: Person) => {
        console.log(response);
        this.getPeople();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchPeople(key: string): void {
    console.log(key);
    const results: Person[] = [];

    // @ts-ignore
    for (const person of this.people) {
      if (person.email.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(person);
      }
    }
    this.people = results;
    if (results.length === 0 || !key) {
      this.getPeople();
    }
  }
}



