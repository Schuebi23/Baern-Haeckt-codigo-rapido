import {Component, inject} from '@angular/core';
import {GroupsStore} from './groups.store';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.html',
  styleUrls: ['./groups.css'],
  providers: [GroupsStore],
})
export class GroupsComponent {
  readonly store = inject(GroupsStore);

  constructor() {
    this.store.loadFromServer();
  }
}
