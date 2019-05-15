export class User{
    cnp: number;
    email: string;
    password: string;
    name: string;
    adress: string;
    phone: number;
    type: string;

    public User(){
        this.cnp=0;
        this.email="";
        this.password="";
        this.name="";
        this.adress="";
        this.phone=0;
        this.type="";
    }
}