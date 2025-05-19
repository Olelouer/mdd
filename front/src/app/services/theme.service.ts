import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Theme } from '../interfaces/theme/theme.interface';
import { ThemesList } from '../interfaces/theme/themesList.interface';

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
}
