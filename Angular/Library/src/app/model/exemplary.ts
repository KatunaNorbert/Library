import { Book } from './book';
export class Exemplary{
    id: number;
    state: string;
    book: Book;

    public Book(){
        this.id=0;
        this.state="";
        this.book=null;
    }
}