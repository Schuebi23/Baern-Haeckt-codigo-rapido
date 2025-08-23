import {Routes} from '@angular/router';
import {GroupsComponent} from './groups/groups.component';
import {GroupComponent} from './group/group.component';
import {AddItem} from './add-item/add-item';
import {SettlementComponent} from './settlement/settlement.component';

export const routes: Routes = [
  {path: '', redirectTo: '/groups', pathMatch: 'full'},
  {path: 'groups', component: GroupsComponent},
  {path: 'group/:groupId', component: GroupComponent},
  {path: 'group/:groupId/add-item', component: AddItem},
  {path: 'group/:groupId/settlement', component: SettlementComponent},
];
