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

}
