import {Component, inject, OnInit} from '@angular/core';
import {GroupStore} from './group.store';
import {ActivatedRoute} from '@angular/router';
import {Item, Unit} from './group';

@Component({
  selector: 'app-group',
  templateUrl: './group.html',
  styleUrls: ['./group.css'],
  providers: [GroupStore],
})
export class GroupComponent implements OnInit {

  readonly store = inject(GroupStore);
  private route = inject(ActivatedRoute);

  constructor() {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const groupId = +params['groupId'];
      const memberId = +params['memberId'];

      if (groupId && memberId) {
        this.store.setInitialData(groupId, memberId);
        this.store.loadItems();
      }
    });
  }

  getTotalRequests(item: Item): number {
    return item.requests.reduce((sum, request) => sum + request.quantity, 0);
  }


  getTotalCommits(item: Item): number {
    return item.commits.reduce((sum, commit) => sum + commit.quantity, 0);
  }

  // Get unit suffix for display
  getUnitSuffix(unit: Unit): string {
    switch (unit) {
      case Unit.L:
        return 'L';
      case Unit.KG:
        return 'kg';
      case Unit.PIECE:
        return '';
      default:
        return '';
    }
  }

  getQuantityDisplay(item: Item): string {
    const totalRequests = this.getTotalRequests(item);
    const totalCommits = this.getTotalCommits(item);
    const unitSuffix = this.getUnitSuffix(item.unit);

    if (unitSuffix) {
      return `${totalRequests}${unitSuffix} / ${totalCommits}${unitSuffix}`;
    }
    return `${totalRequests} / ${totalCommits}`;
  }

  addItem(): void {
    console.log('Add item clicked');

    const newItem: Item = {
      id: Date.now(),
      name: 'Neuer Artikel',
      unit: Unit.PIECE,
      requests: [],
      commits: [],
    };

    this.store.addItem(newItem);
  }
}
