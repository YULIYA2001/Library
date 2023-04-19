import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {GenreService} from "./services/genre.service";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {PersonService} from "./services/person.service";
import {HomePageComponent} from './components/home-page/home-page.component';
import {AuthorPageComponent} from './components/author-page/author-page.component';
import {GenrePageComponent} from './components/genre-page/genre-page.component';
import {PersonPageComponent} from './components/person-page/person-page.component';
import {RouterModule} from "@angular/router";
import {LoginPageComponent} from './components/login-page/login-page.component';
import {BookService} from "./services/book.service";
import {AuthorService} from "./services/author.service";

const routes = [
  {path: '', component: HomePageComponent},
  {path: 'author', component: AuthorPageComponent},
  {path: 'genre', component: GenrePageComponent},
  {path: 'user', component: PersonPageComponent},
  {path: 'login', component: LoginPageComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    AuthorPageComponent,
    GenrePageComponent,
    PersonPageComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [GenreService, PersonService, BookService, AuthorService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
