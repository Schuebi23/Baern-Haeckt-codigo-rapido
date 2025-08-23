import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';

import { Item, Unit } from './group';


@Injectable({providedIn: 'root'})
export class GroupService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  /** GET /groups — List groups */
  async loadItems(): Promise<Item[]> {
    const items: Item[] = [
      {
        id: 1,
        name: "Milch",
        description: undefined,
        unit: Unit.L,
        requests: [
          { id: 1, memberId: 1, quantity: 2, forEveryone: false },
          { id: 2, memberId: 2, quantity: 5, forEveryone: false }
        ],
        commits: [],
        product: undefined
      },
      {
        id: 2,
        name: "Brot",
        description: undefined,
        unit: Unit.PIECE,
        requests: [
          { id: 3, memberId: 1, quantity: 1, forEveryone: false }
        ],
        commits: [
          { id: 1, memberId: 2, quantity: 3, price: 2.50, commited: true }
        ],
        product: undefined
      },
      {
        id: 3,
        name: "Äpfel",
        description: undefined,
        unit: Unit.KG,
        requests: [
          { id: 4, memberId: 3, quantity: 7, forEveryone: false }
        ],
        commits: [
          { id: 2, memberId: 1, quantity: 10, price: 15.00, commited: true }
        ],
        product: undefined
      },
      {
        id: 4,
        name: "Käse",
        description: undefined,
        unit: Unit.KG,
        requests: [],
        commits: [
          { id: 3, memberId: 2, quantity: 2, price: 8.50, commited: false }
        ],
        product: undefined
      },
      {
        id: 5,
        name: "Butter",
        description: undefined,
        unit: Unit.PIECE,
        requests: [
          { id: 5, memberId: 1, quantity: 1, forEveryone: false }
        ],
        commits: [
          { id: 4, memberId: 1, quantity: 1, price: 3.20, commited: true }
        ],
        product: undefined
      }
    ];

    return Promise.resolve(items);
    /**const headers = new HttpHeaders({ Accept: 'application/json' });
    return await firstValueFrom(
      this.http.get<Item[]>(`${this.baseUrl}/items`, { headers })
    );*/
  }

  async createItem(item: Item): Promise<Item> {
    const headers = new HttpHeaders({ Accept: 'application/json' });
    return await firstValueFrom(
      this.http.post<Item>(`${this.baseUrl}/items`, item, { headers })
    );
  }
}
