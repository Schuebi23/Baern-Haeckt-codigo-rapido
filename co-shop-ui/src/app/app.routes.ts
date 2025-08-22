import { Routes } from '@angular/router';
import {GroupsComponent} from './groups/groups.component';

export const routes: Routes = [
  { path: '', redirectTo: '/groups', pathMatch: 'full' },
  { path: 'groups', component: GroupsComponent },
];
