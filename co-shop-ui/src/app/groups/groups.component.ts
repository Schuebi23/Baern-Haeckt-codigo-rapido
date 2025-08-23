import {Component, inject} from '@angular/core';
import {GroupsStore} from './groups.store';
import {TextModalComponent} from '../text-modal/text-modal.component';
import {Router} from '@angular/router';

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

  constructor(private router: Router) {
    this.store.loadFromServer();
  }

  createGroup(name: string) {
    this.store.createGroup(name);
    this.closeModal();
  }

  joinGroup(code: string) {
    console.log('Code:', code);
    this.closeModal();
  }

  closeModal() {
    this.openedModal = null;
  }

  selectGroup(groupId: number) {
    this.router.navigate(['groups', groupId]);
  }
}
