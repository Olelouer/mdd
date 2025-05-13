export interface RegisterRequest {
    username: string;
    email: string;
    password: string;
}

export interface AuthenticationResponse {
    token: string;
}

export interface AuthenticationRequest {
    email?: string;
    username?: string;
    password: string;
}