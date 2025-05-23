import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalMessageResponse } from '../interfaces/globalMessageResponse.interface';
import { CommentResquest } from '../interfaces/comment/commentRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = '/api/comments';

  constructor(private http: HttpClient) { }

  postComment(payload: CommentResquest): Observable<GlobalMessageResponse> {
    return this.http.post<GlobalMessageResponse>(`${this.apiUrl}`, payload);
  }
}