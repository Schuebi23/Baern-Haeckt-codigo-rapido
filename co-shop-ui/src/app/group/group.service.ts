import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';

import {Commit, Group, Item, ItemRequest, Member} from './group';

@Injectable({providedIn: 'root'})
export class GroupService {

  private baseUrl = 'http://localhost:8080';

  constructor(private readonly httpClient: HttpClient) {
  }

  async loadItems(groupId: number): Promise<Item[]> {
    return await firstValueFrom(
        this.httpClient.get<Item[]>(`${this.baseUrl}/items/${groupId}`, {}),
    );
  }

  async createItem(item: Item): Promise<Item> {
    return await firstValueFrom(
        this.httpClient.post<Item>(`${this.baseUrl}/items`, item, {}),
    );
  }

  async loadGroup(groupId: number): Promise<Group> {
    return await firstValueFrom(
        this.httpClient.get<Group>(`${this.baseUrl}/groups/${groupId}`, {}),
    );
  }

  async loadMembers(groupId: number): Promise<Member[]> {
    return await firstValueFrom(
        this.httpClient.get<Member[]>(`${this.baseUrl}/groups/${groupId}/members`, {}),
    );
  }

  async createCommit(itemId: number, commitAmount: number, memberId: number): Promise<Commit> {
    return await firstValueFrom(
        this.httpClient.post<Commit>(`${this.baseUrl}/commits`, {
          'memberId': memberId,
          'itemId': itemId,
          'qtyCommitted': commitAmount,
          'price': 0,
          'commited': false,
        }, {}),
    );
  }

  async updateCommit(commitId: number, newAmount: number, price: number = 0, commited: boolean = false): Promise<Commit> {
    return await firstValueFrom(
        this.httpClient.patch<Commit>(`${this.baseUrl}/commits/${commitId}`, {
          'qtyCommitted': newAmount,
          'price': price,
          'commited': commited,
        }, {}),
    );
  }

  async createRequest(itemId: number, requestAmount: number, memberId: number, forEveryone: boolean = false): Promise<ItemRequest> {
    return await firstValueFrom(
        this.httpClient.post<ItemRequest>(`${this.baseUrl}/requests`, {
          'memberId': memberId,
          'itemId': itemId,
          'qtyRequested': requestAmount,
          'forEveryone': forEveryone,
        }, {}),
    );
  }

  async updateRequest(requestId: number, newAmount: number, forEveryone: boolean = false): Promise<ItemRequest> {
    return await firstValueFrom(
        this.httpClient.patch<ItemRequest>(`${this.baseUrl}/requests/${requestId}`, {
          'qtyRequested': newAmount,
          'forEveryone': forEveryone,
        }, {}),
    );
  }
}
