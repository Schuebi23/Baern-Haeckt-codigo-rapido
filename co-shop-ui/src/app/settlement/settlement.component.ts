import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GroupService} from '../group/group.service';

@Component({
  selector: 'app-settlement',
  imports: [],
  templateUrl: './settlement.html',
  styleUrl: './settlement.css'
})
export class SettlementComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private groupService = inject(GroupService);

  public settlements: string[] = [];


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const groupId = +params['groupId'];

      if (groupId) {
        this.groupService.getSettlement(groupId).then(res => {
          this.settlements = res;
        });
      }
    });
  }

}
