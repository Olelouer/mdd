import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { RegisterRequest } from '../interfaces/auth/registerRequest.interface';
import { AuthenticationRequest } from '../interfaces/auth/authenticationRequest.interface';
import { AuthenticationResponse } from '../interfaces/auth/authenticationResponse.interface';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = '/api/auth';
    private readonly TOKEN_KEY = 'authToken';

    constructor(private http: HttpClient) { }

    register(registerData: RegisterRequest): Observable<AuthenticationResponse> {
        return this.http.post<AuthenticationResponse>(`${this.apiUrl}/register`, registerData)
            .pipe(
                tap(response => {
                    this.storeToken(response.token);
                })
            )
    }

    login(loginData: AuthenticationRequest): Observable<AuthenticationResponse> {
        return this.http.post<AuthenticationResponse>(`${this.apiUrl}/login`, loginData)
            .pipe(
                tap(response => {
                    this.storeToken(response.token);
                })
            )
    }

    storeToken(token: string): void {
        localStorage.setItem(this.TOKEN_KEY, token);
    }

    getToken(): string | null {
        return localStorage.getItem(this.TOKEN_KEY);
    }

    isLoggedIn(): boolean {
        return !!this.getToken();
    }

    logout(): void {
        localStorage.removeItem(this.TOKEN_KEY);
    }
}