import {inject, Injectable} from '@angular/core';
import {patchState, signalStore, withMethods, withState} from '@ngrx/signals';
import {LocalStorageService} from '../general/local-storage.service';
import {AddItemService} from './add-item.service';

type AddItemState = {
  userId: number;
  loading: boolean;
  groupId: number;
};

const initialState: AddItemState = {userId: 1, groupId: 1, loading: false};

@Injectable({providedIn: 'root'})
export class AddItemStore extends signalStore(
  withState(initialState),
  withMethods((store) => {
    const addItemService = inject(AddItemService);
    const localStorageService = inject(LocalStorageService);
    return {
      createItem(){
        //addItemService.createItem(store.groupId, )
        patchState(store, {loading: true});
      }
    }
  })) {
}
