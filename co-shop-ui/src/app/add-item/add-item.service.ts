import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';
import {ItemCreate} from '../group/group';

@Injectable({providedIn: 'root'})
export class AddItemService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  async createItem(groupId: number, item: ItemCreate): Promise<Group[]> {
    const headers = new HttpHeaders({Accept: 'application/json'});
    return await firstValueFrom(
      this.http.post<Group[]>(`${this.baseUrl}/items/${groupId}`,
        {
          "name": item.name,
          "productId": item.productId,
          "unit": item.unit,
          "description": item.description,
        }, {headers})
    );
  }
}
