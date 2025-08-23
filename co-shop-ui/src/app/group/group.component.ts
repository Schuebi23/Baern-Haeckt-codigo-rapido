import {Component, inject, OnInit} from '@angular/core';
import {GroupStore} from './group.store';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {Item} from './group';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-group',
  templateUrl: './group.html',
  styleUrls: ['./group.css'],
  providers: [GroupStore],
  imports: [
    RouterLink,
    FormsModule,
  ],
})
export class GroupComponent implements OnInit {

  readonly store = inject(GroupStore);
  private readonly route = inject(ActivatedRoute);

  readonly expandedItems = new Set<number>();

  isCommitModalOpen = false;
  isRequestModalOpen = false;
  selectedItem: Item | undefined = undefined;
  commitAmount = 0;
  requestAmount = 0;

  constructor() {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const groupId = +params['groupId'];

      if (groupId) {
        this.store.loadInitialData(groupId);
      }
    });
  }

  toggleItemExpanded(itemId: number): void {
    if (this.expandedItems.has(itemId)) {
      this.expandedItems.delete(itemId);
    } else {
      this.expandedItems.add(itemId);
    }
  }

  isItemExpanded(itemId: number): boolean {
    return this.expandedItems.has(itemId);
  }

  openCommitModal(item: Item): void {
    this.selectedItem = item;
    this.commitAmount = this.store.getCurrentCommitAmount(item); // Reset to default
    this.isCommitModalOpen = true;
  }

  closeCommitModal(): void {
    this.isCommitModalOpen = false;
    this.selectedItem = undefined;
    this.commitAmount = 1;
  }

  confirmCommit(): void {
    if (this.selectedItem) {
      this.store.createOrUpdateCommit(this.selectedItem.id, this.commitAmount);
      this.closeCommitModal();
    }
  }

  openRequestModal(item: Item): void {
    this.selectedItem = item;
    this.requestAmount= this.store.getCurrentRequestAmount(item); // Reset to default
    this.isRequestModalOpen = true;
  }

  closeRequestModal(): void {
    this.isRequestModalOpen = false;
    this.selectedItem = undefined;
    this.requestAmount = 1;
  }

  confirmRequest(): void {
    if (this.selectedItem) {
      this.store.createOrUpdateRequest(this.selectedItem.id, this.requestAmount);
      this.closeRequestModal();
    }
  }
}
