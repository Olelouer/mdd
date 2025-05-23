import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalMessageResponse } from '../interfaces/globalMessageResponse.interface';
import { HttpClient } from '@angular/common/http';
import { UserRequest } from '../interfaces/user/userRequest';
import { UserInfos } from '../interfaces/user/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = '/api/users';

  constructor(private http: HttpClient) { }

  getCurrentUserInfos(): Observable<UserInfos> {
    return this.http.get<UserInfos>(`${this.apiUrl}/me`);
  }

  updateCurrentUser(payload: UserRequest): Observable<GlobalMessageResponse> {
    return this.http.put<GlobalMessageResponse>(`${this.apiUrl}/me`, payload);
  }
}
