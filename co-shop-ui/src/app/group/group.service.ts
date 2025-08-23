import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';

import {Group, Item, Member} from './group';

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
}
