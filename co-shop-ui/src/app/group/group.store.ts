import {inject, Injectable} from '@angular/core';
import {patchState, signalStore, withMethods, withState} from '@ngrx/signals';
import {GroupService} from './group.service';

import { Item, Unit } from './group';

type GroupState = {
  items: Item[];
  loading: boolean;
  memberId?: number;
  groupId?: number;
}

const initialState: GroupState = {items: [], loading: false};

@Injectable({providedIn: 'root'})
export class GroupStore extends signalStore(
  withState(initialState),
  withMethods((store) => {
    const groupService = inject(GroupService);

    return {
      setInitialData: (groupId: number, memberId: number) => {
        patchState(store, {groupId, memberId});
      },
      loadItems: async () => {
        console.log("Get items");
        patchState(store, {loading: true});
        try {
          const items = await groupService.loadItems();
          console.log("Items loaded")
          patchState(store, {items, loading: false});
        } catch (e: any) {
          console.log("Errror", e)
          patchState(store, {loading: false});
        }
      },
      addItem: async (item: Item) => {
        try {
          await groupService.createItem(item);
        } catch (e: any) {

        }
      },
    };
  }),
) {
}
