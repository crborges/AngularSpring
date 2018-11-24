/* da o comando export do js para poder usar a classe fora ddaqui em outros pontos */
/* cria uma classe poj bem simplisinha de pessoa e tal */
export class Person{
    /* um atributo bem comuno de pessoa o seu nome  */
    private nome: string;
    private idade: number;
    private telefone: string;

    /* um construtor bem, padrão tipo mais simples de java em que incia os parametros */
    constructor(nome :string)                           {this.nome=nome;}
    public setNome(nome :string)            :void       {this.nome=nome;}
    public setIdade(idade :number)          :void       {this.idade=idade;}
    public setTelefone(telefone :string)    :void       {this.telefone=telefone;}
    public getNome()                        :string     {return this.nome;}
    public getIdade()                       :number     {return this.idade;}
    public getTelefone()                    :string     {return this.telefone;}
    /* um metodo bem to string que vai msotrar os dados do cara ou até mesmo um get de algo 
     */
    public ToString():string{
        /* para iterar variaveis dentro do codigo com o this use as crases ´para msotrar */
        return ` o nome eh ${this.nome} 
                a idade eh ${this.idade}
                o telefone eh ${this.telefone}`;
    }

    public Equals(Person: any) :boolean{return this===Person;}

}