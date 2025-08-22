import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {firstValueFrom, Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class GroupsService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  /** GET /groups — List groups */
  async listGroups(): Promise<Group[]> {
    const headers = new HttpHeaders({ Accept: 'application/json' });
    return await firstValueFrom(
      this.http.get<Group[]>(`${this.baseUrl}/groups`, { headers })
    );
  }
}
