import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { ArticlesList } from '../interfaces/article/articlesList.interface';
import { Article } from '../interfaces/article/article.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private apiUrl = '/api/articles';

  constructor(private http: HttpClient) { }

  getUserFeed(): Observable<Article[]> {
    const params = new HttpParams().set('sort', 'createdAt,asc');

    return this.http.get<ArticlesList>(`${this.apiUrl}/feed`, { params }).pipe(
      map(response => {
        return response.articles;
      }),
      catchError(this.handleError)
    )
  }

  private handleError(error: any): Observable<never> {
    console.error('Une erreur est survenue dans ArticleService:', error);
    return throwError(() => new Error('Erreur lors de la récupération des articles. Veuillez réessayer.'));
  }
}
