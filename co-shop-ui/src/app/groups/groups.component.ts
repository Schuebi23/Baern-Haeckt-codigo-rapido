import {Component, inject} from '@angular/core';
import {GroupsStore} from './groups.store';
import {TextModalComponent} from '../text-modal/text-modal.component';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.html',
  styleUrls: ['./groups.css'],
  providers: [GroupsStore],
  imports: [
    TextModalComponent
  ]
})
export class GroupsComponent {
  readonly store = inject(GroupsStore);
  openedModal: 'join' | 'create' | null = null;

  constructor() {
    this.store.loadFromServer();
  }

  createGroup(name: string) {
    console.log('Name:', name);
    this.closeModal();
  }

  joinGroup(code: string) {
    console.log('Code:', code);
    this.closeModal();
  }

  closeModal() {
    this.openedModal = null;
  }
}
