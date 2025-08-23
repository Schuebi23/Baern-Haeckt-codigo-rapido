import {inject, Injectable} from '@angular/core';
import {patchState, signalStore, withMethods, withState} from '@ngrx/signals';
import {GroupsService} from './groups.service';
import {LocalStorageService} from '../general/local-storage.service';

type GroupsState = {
  userId: number;
  groups: Group[];
  loading: boolean;
};

const initialState: GroupsState = {userId: 1, groups: [], loading: false};

@Injectable({providedIn: 'root'})
export class GroupsStore extends signalStore(
  withState(initialState),
  withMethods((store) => {
    const groupsService = inject(GroupsService);
    const localStorageService = inject(LocalStorageService);
    return {
      setUserId(userId: number) {
        localStorageService.setItem('memberId', userId);
        patchState(store, {userId: userId})
      },
      loadFromServer: async () => {
        patchState(store, {loading: true});
        try {
          const data = await groupsService.listGroups();
          patchState(store, {groups: data, loading: false});
        } catch (e: any) {
          patchState(store, {loading: false});
        }
      },
      createGroup: async (name: string) => {
        await groupsService.createGroup(name);
        await groupsService.listGroups();
      }
    };
  }),
) {
}
