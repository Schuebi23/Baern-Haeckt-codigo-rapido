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
  price = 0.0;
  requestAmount = 0;
  forEveryone = false;

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
    const currentCommit = this.store.getCurrentCommit(item);
    if (currentCommit) {
      this.commitAmount = currentCommit.qtyCommitted;
      this.price = currentCommit.price ? currentCommit.price : 0.0;
    }
    this.isCommitModalOpen = true;
  }

  closeCommitModal(): void {
    this.isCommitModalOpen = false;
    this.selectedItem = undefined;
    this.commitAmount = 1;
  }

  confirmCommit(): void {
    if (this.selectedItem) {
      this.store.createOrUpdateCommit(this.selectedItem.id, this.commitAmount, this.price);
      this.closeCommitModal();
    }
  }

  openRequestModal(item: Item): void {
    this.selectedItem = item;
    const currentRequest = this.store.getCurrentRequest(item); // Reset to default
    this.requestAmount = currentRequest ? currentRequest.qtyRequested : 0;
    this.forEveryone = currentRequest ? currentRequest.forEveryone : false;
    this.isRequestModalOpen = true;
  }

  closeRequestModal(): void {
    this.isRequestModalOpen = false;
    this.selectedItem = undefined;
    this.requestAmount = 1;
  }

  confirmRequest(): void {
    if (this.selectedItem) {
      this.store.createOrUpdateRequest(this.selectedItem.id, this.requestAmount, this.forEveryone);
      this.closeRequestModal();
    }
  }
}
