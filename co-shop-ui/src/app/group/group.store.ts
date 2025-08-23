import {inject, Injectable} from '@angular/core';
import {patchState, signalStore, withMethods, withState} from '@ngrx/signals';
import {GroupService} from './group.service';

import {Commit, Item, ItemRequest, Member, RequestCommitForDisplay} from './group';
import {LocalStorageService} from '../general/local-storage.service';

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

const findCommitByItemAndMember = (item: Item, memberId: number): Commit | undefined => {
  return item.commits.find(c => c.memberId === memberId);
};

const findRequestByItemAndMember = (item: Item, memberId: number): ItemRequest | undefined => {
  return item.requests.find(r => r.memberId === memberId);
};

@Injectable({providedIn: 'root'})
export class GroupStore extends signalStore(
    withState(initialState),
    withMethods((store) => {
      const groupService = inject(GroupService);
      const localStorageService = inject(LocalStorageService);

      return {
        loadInitialData: async (groupId: number): Promise<void> => {
          const memberId = localStorageService.getItem<number>('memberId');

          if (memberId) {
            const group = await groupService.loadGroup(groupId);
            const members = await groupService.loadMembers(groupId);
            const items = await groupService.loadItems(groupId);
            patchState(store, {group, memberId, members, items});
          }
        },
        loadItems: async (): Promise<void> => {
          const items = await groupService.loadItems(store.group().id);
          patchState(store, {items});
        },
        getTotalRequestsForItem: (itemId: number): number => {
          const item = findItemById(store.items(), itemId);
          return item ? item.requests.reduce((sum, request) => sum + request.qtyRequested, 0) : 0;
        },
        getTotalCommitsForItem: (itemId: number): number => {
          const item = findItemById(store.items(), itemId);
          return item ? item.commits.reduce((sum, request) => sum + request.qtyCommitted, 0) : 0;
        },
        getMemberNameById: (memberId: number): string => {
          const member = findMemberById(store.members(), memberId);
          return member ? member.displayName : '';
        },
        getCurrentCommitAmount: (currentItem?: Item): number => {
          if (currentItem) {
            const commit = findCommitByItemAndMember(currentItem, store.memberId());
            return commit ? commit.qtyCommitted : 0;
          }

          return 1;
        },
        getCurrentRequestAmount: (currentItem: Item): number => {
          if (currentItem) {
            const request = findRequestByItemAndMember(currentItem, store.memberId());
            return request ? request.qtyRequested : 0;
          }

          return 1;
        },
        getCommitsForItemAndMember: (itemId: number, memberId: number): string => {
          const item = findItemById(store.items(), itemId);
          if (item) {
            const commit = findCommitByItemAndMember(item, memberId);
            if (commit) {
              return `${commit.qtyCommitted}`;
            }
            return '0';
          }
          return 'Nan';
        },
        getRequestsForItemAndMember: (itemId: number, memberId: number): string => {
          const item = findItemById(store.items(), itemId);
          if (item) {
            const commit = findRequestByItemAndMember(item, memberId);
            if (commit) {
              return `${commit.qtyRequested}`;
            }
            return '0';
          }
          return 'Nan';
        },
        createOrUpdateCommit: async (itemId: number, commitAmount: number): Promise<void> => {
          const currentItems = store.items();
          const targetItem = findItemById(currentItems, itemId);

          if (!targetItem) {
            console.error(`Item with ID ${itemId} not found`);
            return;
          }

          const existingCommit = targetItem.commits.find(c => c.memberId === store.memberId());

          if (existingCommit && existingCommit.id) {
            const updatedCommit = await groupService.updateCommit(existingCommit.id, commitAmount);

            const updatedItems = currentItems.map(item => {
              if (item.id === itemId) {
                return {
                  ...item,
                  commits: item.commits.map(commit =>
                      commit.id === existingCommit.id ? updatedCommit : commit,
                  ),
                };
              }
              return item;
            });

            patchState(store, {items: updatedItems});
          } else {
            const createdCommit = await groupService.createCommit(itemId, commitAmount, store.memberId());

            const updatedItems = currentItems.map(item => {
              if (item.id === itemId) {
                return {
                  ...item,
                  commits: [...item.commits, createdCommit],
                };
              }
              return item;
            });
            patchState(store, {items: updatedItems});
          }
        },
        createOrUpdateRequest: async (itemId: number, requestAmount: number): Promise<void> => {
          const currentItems = store.items();
          const targetItem = findItemById(currentItems, itemId);

          if (!targetItem) {
            console.error(`Item with ID ${itemId} not found`);
            return;
          }

          const existingRequest = targetItem.requests.find(c => c.memberId === store.memberId());

          if (existingRequest && existingRequest.id) {
            const updatedRequest = await groupService.updateRequest(existingRequest.id, requestAmount);

            const updatedItems = currentItems.map(item => {
              if (item.id === itemId) {
                return {
                  ...item,
                  requests: item.requests.map(request =>
                      request.id === existingRequest.id ? updatedRequest : request,
                  ),
                };
              }
              return item;
            });

            patchState(store, {items: updatedItems});
          } else {
            const createdRequest = await groupService.createRequest(itemId, requestAmount, store.memberId());

            const updatedItems = currentItems.map(item => {
              if (item.id === itemId) {
                return {
                  ...item,
                  requests: [...item.requests, createdRequest],
                };
              }
              return item;
            });
            patchState(store, {items: updatedItems});
          }
        },
        getRequestAndCommitsCombined: (itemId: number): RequestCommitForDisplay[] => {
          const item = findItemById(store.items(), itemId);
          if (!item) return [];

          const memberDataMap = new Map<number, RequestCommitForDisplay>();

          item.requests.forEach(request => {
            const existing = memberDataMap.get(request.memberId);
            if (existing) {
              existing.qtyRequested += request.qtyRequested;
              existing.forEveryone = existing.forEveryone || request.forEveryone;
            } else {
              memberDataMap.set(request.memberId, {
                memberId: request.memberId,
                qtyRequested: request.qtyRequested,
                qtyCommitted: 0,
                forEveryone: request.forEveryone,
                price: 0,
                commited: false,
              });
            }
          });

          item.commits.forEach(commit => {
            const existing = memberDataMap.get(commit.memberId);
            if (existing) {
              existing.qtyCommitted += commit.qtyCommitted;
              existing.price = commit.price || 0; // Use the commit's price
              existing.commited = commit.commited;
            } else {
              memberDataMap.set(commit.memberId, {
                memberId: commit.memberId,
                qtyRequested: 0,
                qtyCommitted: commit.qtyCommitted,
                forEveryone: false,
                price: commit.price || 0,
                commited: commit.commited,
              });
            }
          });

          return Array.from(memberDataMap.values());
        },
      };
    }),
) {

}
