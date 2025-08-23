import {inject, Injectable} from '@angular/core';
import {patchState, signalStore, withMethods, withState} from '@ngrx/signals';
import {GroupService} from './group.service';

import {Item} from './group';
import {LocalStorageService} from '../general/local-storage-service';

type GroupState = {
  items: Item[];
  memberId: number;
  groupId: number;
}

const initialState: GroupState = {items: [], groupId: -1, memberId: 1};

const findItemById = (items: Item[], itemId: number): Item | undefined => {
  return items.find(i => i.id === itemId);
};

@Injectable({providedIn: 'root'})
export class GroupStore extends signalStore(
    withState(initialState),
    withMethods((store) => {
      const groupService = inject(GroupService);
      const localStorageService = inject(LocalStorageService);

      return {
        setInitialData: (groupId: number) => {
          const memberId = localStorageService.getItem<number>('memberId');
          if (memberId) {
            patchState(store, {groupId, memberId});
          }
        },
        loadItems: async () => {
          const items = await groupService.loadItems(store.groupId());
          patchState(store, {items});
        },
        addItem: async (item: Item) => {
          try {
            await groupService.createItem(item);
          } catch (e: any) {

          }
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
      };
    }),
) {
}
