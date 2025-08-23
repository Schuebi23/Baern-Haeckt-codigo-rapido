import {Component, inject} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from './general/local-storage.service';
import {Member} from './group/group';
import {of} from 'rxjs';
import {AsyncPipe} from '@angular/common';

@Component({
  imports: [RouterModule, AsyncPipe],
  selector: 'app-root',
  styles: `
    header {
      max-width: 400px;
      margin: 0 auto .5rem auto;
    }
  `,
  template: `
    @let member = member$ | async;
    @if (member) {
      <header>Hallo {{ member.displayName }}</header>
    }
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  private readonly localStorageService = inject(LocalStorageService);
  private readonly httpClient = inject(HttpClient);
  private readonly memberId: number | null = this.localStorageService.getItem('memberId');
  readonly member$ = this.memberId ? this.httpClient.get<Member>('http://localhost:8080/members/' + this.memberId) : of(null);
}
