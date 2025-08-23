import {Routes} from '@angular/router';
import {GroupsComponent} from './groups/groups.component';
import {GroupComponent} from './group/group.component';

export const routes: Routes = [
  {path: '', redirectTo: '/groups', pathMatch: 'full'},
  {path: 'groups', component: GroupsComponent},
  {path: 'group/:groupId', component: GroupComponent},
];
