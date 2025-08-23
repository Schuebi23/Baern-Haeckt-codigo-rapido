import {Component, inject, OnInit} from '@angular/core';
import {GroupStore} from './group.store';
import {ActivatedRoute, RouterLink} from '@angular/router';

@Component({
  selector: 'app-group',
  templateUrl: './group.html',
  styleUrls: ['./group.css'],
  providers: [GroupStore],
  imports: [
    RouterLink
  ]
})
export class GroupComponent implements OnInit {

  readonly store = inject(GroupStore);
  private readonly route = inject(ActivatedRoute);

  readonly expandedItems = new Set<number>();

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
}
