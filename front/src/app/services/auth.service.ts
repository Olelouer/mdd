import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { RegisterRequest, AuthenticationRequest, AuthenticationResponse } from '../interfaces/auth.interface';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = '/api/auth';

    constructor(private http: HttpClient) { }

    register(registerData: RegisterRequest): Observable<AuthenticationResponse> {
        return this.http.post<AuthenticationResponse>(`${this.apiUrl}/register`, registerData)
            .pipe(
                tap(response => {
                    console.log(response);
                    this.storeToken(response.token);
                })
            )
    }

    login(loginData: AuthenticationRequest): Observable<AuthenticationResponse> {
        return this.http.post<AuthenticationResponse>(`${this.apiUrl}/login`, loginData)
            .pipe(
                tap(response => {
                    console.log(response);
                    this.storeToken(response.token);
                })
            )
    }

    storeToken(token: string): void {
        localStorage.setItem('authToken', token);
    }

    logout(): void {
        localStorage.removeItem('authToken');
    }
}