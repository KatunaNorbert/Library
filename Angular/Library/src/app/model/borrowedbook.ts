import { User } from './user';
export class BorrowedBook{
    id: number;
    date: string;
    exemplary: number;
    user: User;

    public Book(){
        this.id=0;
        this.date="";
        this.exemplary=0;
        this.user=null;
    }
}