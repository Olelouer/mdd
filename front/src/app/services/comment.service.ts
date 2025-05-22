import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalMessageResponse } from '../interfaces/globalMessageResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = '/api/comments';

  constructor(private http: HttpClient) { }

  postComment(payload: { content: string, articleId: string }): Observable<GlobalMessageResponse> {
    return this.http.post<GlobalMessageResponse>(`${this.apiUrl}`, payload);
  }
}