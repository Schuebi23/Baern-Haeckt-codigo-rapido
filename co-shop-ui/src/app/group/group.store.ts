import {inject, Injectable} from '@angular/core';
import {patchState, signalStore, withMethods, withState} from '@ngrx/signals';
import {GroupService} from './group.service';

import {Item, Member} from './group';
import {LocalStorageService} from '../general/local-storage-service';

type GroupState = {
  items: Item[];
  members: Member[];
  group: Group;
  memberId: number;
}
const EMPTY_GROUP: Group = {id: -1, name: '', startDate: new Date(), endDate: new Date()} as const;

const initialState: GroupState = {items: [], members: [], group: EMPTY_GROUP, memberId: -1};

const findItemById = (items: Item[], itemId: number): Item | undefined => {
  return items.find(i => i.id === itemId);
};

const findMemberById = (member: Member[], memberId: number): Member | undefined => {
  return member.find(m => m.id === memberId);
};

@Injectable({providedIn: 'root'})
export class GroupStore extends signalStore(
    withState(initialState),
    withMethods((store) => {
      const groupService = inject(GroupService);
      const localStorageService = inject(LocalStorageService);

      return {
        loadInitialData: async (groupId: number) => {
          const memberId = localStorageService.getItem<number>('memberId');

          if (memberId) {
            const group = await groupService.loadGroup(groupId);
            const members = await groupService.loadMembers(groupId);
            const items = await groupService.loadItems(groupId);
            patchState(store, {group, memberId, members, items});
          }
        },
        loadItems: async () => {
          const items = await groupService.loadItems(store.group().id);
          patchState(store, {items});
        },
        getItem: (itemId: number) => {
          return findItemById(store.items(), itemId);
        },
        getTotalRequestsForItem: (itemId: number): number => {
          const item = findItemById(store.items(), itemId);
          return item ? item.requests.reduce((sum, request) => sum + request.qtyRequested, 0) : 0;
        },
        getTotalCommitsForItem: (itemId: number): number => {
          const item = findItemById(store.items(), itemId);
          return item ? item.commits.reduce((sum, request) => sum + request.quantity, 0) : 0;
        },
        getMemberNameById(memberId: number) {
          const member = findMemberById(store.members(), memberId);
          return member ? member.displayName : '';
        },
      };
    }),
) {
}
