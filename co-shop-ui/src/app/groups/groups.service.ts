import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';

@Injectable({providedIn: 'root'})
export class GroupsService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  /** GET /groups — List groups */
  async listGroups(): Promise<Group[]> {
    const headers = new HttpHeaders({ Accept: 'application/json' });
    return await firstValueFrom(
      this.http.get<Group[]>(`${this.baseUrl}/members/1/groups`, { headers })
    );
  }

  async createGroup(name: string, members: number[]): Promise<void> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    await firstValueFrom(
      this.http.post<void>(`${this.baseUrl}/groups`, { name, members }, { headers }));
  }

  async joinGroup(inviteCode: string, memberId: number | null): Promise<void> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    await firstValueFrom(
      this.http.post<void>(`${this.baseUrl}/groups/join`, { inviteCode, memberId }, { headers }));
  }

}
