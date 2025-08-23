import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GroupService} from '../group/group.service';
import {GroupStore} from '../group/group.store';

@Component({
  selector: 'app-settlement',
  imports: [],
  templateUrl: './settlement.html',
  styleUrl: './settlement.css'
})
export class SettlementComponent implements OnInit {
  private route = inject(ActivatedRoute);
  readonly store = inject(GroupStore);

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const groupId = +params['groupId'];

      if (groupId) {
        this.store.loadSettlements(groupId);
      }
    });
  }

}
