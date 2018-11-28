import { Person } from './Person';
/* usando heranca que é bem como java mesmo com extends e tal */
export  class Estudante extends Person {

    private escolaridade : string;

    
    /* crio o construtor e que eu obrigatoriamente tenho que chaamr o super aqui  */
    constructor(name:string)                                    {super(name);}
    public setEscolaridade(escolaridade:string)     :void       { this.escolaridade=escolaridade;}
    public getEscolaridade()                        :string     { return this.escolaridade;}
    public ToString() :string{
    return `super.ToString() 
            a escolaridade e ${this.escolaridade}        
            `;
    }

    
}