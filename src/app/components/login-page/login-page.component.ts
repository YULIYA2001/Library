import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {PersonService} from "../../services/person.service";
import {Person} from "../../models/person";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  email: string | undefined;
  password: string | undefined;
  message: any

  constructor(private personService: PersonService, private router: Router) {
  }

  ngOnInit() {
  }

  public onLoginUser(loginForm: NgForm): void {

    this.personService.loginPerson(loginForm.value.email, loginForm.value.password).subscribe(
      (response: Person) => {
        console.log(response);
        loginForm.reset();
        // @ts-ignore
        this.router.navigate("/");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        loginForm.reset();
      }
    );
  }
}
