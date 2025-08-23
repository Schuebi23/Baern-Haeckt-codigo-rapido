import {Component, inject} from '@angular/core';
import {GroupsStore} from './groups.store';
import {GroupJoinModalComponent} from '../group-join-modal/group-join-modal.component';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.html',
  styleUrls: ['./groups.css'],
  providers: [GroupsStore],
  imports: [
    GroupJoinModalComponent
  ]
})
export class GroupsComponent {
  readonly store = inject(GroupsStore);
  showModal = false;

  constructor() {
    this.store.loadFromServer();
  }

  openModal() {
    this.showModal = true;
  }

  onConfirm(code: string) {
    console.log('Code:', code);
    this.showModal = false;
  }

  onCancel() {
    this.showModal = false;
  }
}
