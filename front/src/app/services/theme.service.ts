import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Theme } from '../interfaces/theme/theme.interface';
import { ThemesList } from '../interfaces/theme/themesList.interface';
import { GlobalMessageResponse } from '../interfaces/globalMessageResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private apiUrl = '/api/themes';

  constructor(private http: HttpClient) { }

  getAllWithSubscriptions(): Observable<Theme[]> {
    return this.http.get<ThemesList>(`${this.apiUrl}/subscriptions`).pipe(
      map(response => {
        return response.themes;
      })
    )
  }

  subscribeCurrentUser(themeId: number): Observable<GlobalMessageResponse> {
    return this.http.post<GlobalMessageResponse>(`${this.apiUrl}/${themeId}/subscribe`, null);
  }

  unsubscribeCurrentUser(themeId: number): Observable<GlobalMessageResponse> {
    return this.http.delete<GlobalMessageResponse>(`${this.apiUrl}/${themeId}/unsubscribe`);
  }
}
