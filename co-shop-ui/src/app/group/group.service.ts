import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';

import {Item} from './group';

@Injectable({providedIn: 'root'})
export class GroupService {

  private baseUrl = 'http://localhost:8080';

  constructor(private readonly httpClient: HttpClient) {
  }

  /** GET /groups — List groups */
  async loadItems(groupId: number): Promise<Item[]> {
    return await firstValueFrom(
        this.httpClient.get<Item[]>(`${this.baseUrl}/items/${groupId}`, {}),
    );
  }

  async createItem(item: Item): Promise<Item> {
    const headers = new HttpHeaders({Accept: 'application/json'});
    return await firstValueFrom(
        this.httpClient.post<Item>(`${this.baseUrl}/items`, item, {headers}),
    );
  }
}
