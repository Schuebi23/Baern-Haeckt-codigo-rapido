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

      if (groupId) {
        this.store.setInitialData(groupId);
        this.store.loadItems();
      }
    });
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
