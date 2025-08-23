import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';
import {Item, ItemCreate, ItemRequest} from '../group/group';

@Injectable({providedIn: 'root'})
export class AddItemService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  async createItem(groupId: number, item: ItemCreate): Promise<Item> {
    const headers = new HttpHeaders({Accept: 'application/json'});
    return await firstValueFrom(
      this.http.post<Item>(`${this.baseUrl}/items/${groupId}`,
        {
          "name": item.name,
          "productId": item.productId,
          "unit": item.unit,
          "description": item.description,
        }, {headers})
    );
  }

  async createRequest(itemRequest: ItemRequest): Promise<ItemRequest> {
    const headers = new HttpHeaders({Accept: 'application/json'});
    return await firstValueFrom(
      this.http.post<ItemRequest>(`${this.baseUrl}/requests`, itemRequest, {headers})
    );
  }
}
