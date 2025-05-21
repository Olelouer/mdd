import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { ArticlesList } from '../interfaces/article/articlesList.interface';
import { Article } from '../interfaces/article/article.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private apiUrl = '/api/articles';

  constructor(private http: HttpClient) { }

  getUserFeed(sortOrder: 'asc' | 'desc' = 'asc'): Observable<Article[]> {
    const params = new HttpParams().set('sort', `createdAt,${sortOrder}`);

    return this.http.get<ArticlesList>(`${this.apiUrl}/feed`, { params }).pipe(
      map(response => {
        return response.articles;
      }),
    )
  }

  getArticle(articleId: string): Observable<Article> {
    return this.http.get<Article>(`${this.apiUrl}/${articleId}`);
  }
}
