import { UserInfos } from "../user/user.interface";

export interface Comment {
    id: number;
    content: string;
    author: UserInfos;
    createdAt: string;
}