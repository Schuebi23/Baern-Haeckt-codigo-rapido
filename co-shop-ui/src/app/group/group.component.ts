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

  // Modal state
  isModalOpen = false;
  selectedItem: Item | null = null;
  commitAmount = 1;

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
    this.commitAmount = 1; // Reset to default
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
    this.selectedItem = null;
    this.commitAmount = 1;
  }

  confirmCommit(): void {
    if (this.selectedItem && this.commitAmount > 0) {
      this.store.createOrUpdateCommit(this.selectedItem.id, this.commitAmount);
      this.closeModal();
    }
  }

  onAmountChange(event: Event): void {
    const target = event.target as HTMLInputElement;
    const value = parseInt(target.value, 10);
    this.commitAmount = isNaN(value) || value < 1 ? 1 : value;
  }
}
