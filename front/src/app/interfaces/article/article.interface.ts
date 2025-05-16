import { Theme } from "../theme/theme.interface";
import { UserInfos } from "../user/user.interface";
import { Comment } from "../comment/comment.interface";

export interface Article {
    id: number;
    title: string;
    content: string;
    createdAt: string;
    updatedAt: string;
    theme: Theme;
    author: UserInfos;
    comments: Comment[];
}