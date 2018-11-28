import { Person } from './../heranca/Person';
import { DaoInterface } from './dao.interface';
import { Estudante } from '../heranca/Estudante';
export class DaoPerson implements DaoInterface{
    tableName: string;   
    
    insert(person: Person): boolean {
        if(person.getIdade()<0){    throw new Error("Pessoa invalida"); }
        console.log(`comitada a pessoa ${person.getNome()} no banco`);
        return true;
    }
    
    update(person: Person): boolean {
       if(person.getIdade()<0){    throw new Error("Pessoa invalida"); }
       console.log(`atualizada a pessoa ${person.getNome()} no banco`);
        return true;
    }
    
    delete(id: number): boolean {
        let person = this.find(id);
        if(id<0) { throw new Error("Pessoa invalida"); }
        console.log(`deletada a pessoa no banco`);
        return true;
    }
    
    find(id: number)  :any{

        if(id>3) { throw new Error("Pessoa invalida"); }
        if(id==1){
            let person = new Person("pessoa");
                person.setIdade(30);
                person.setTelefone("985149816");
                return person.ToString();
        }
        if(id==2){
            let estudante = new Estudante("estudante");
            estudante.setIdade(30);
            estudante.setTelefone("985149816");        
            estudante.setEscolaridade("superior completo");
            return estudante;
        } 
    }
    
    findAll(): [Person] {
        let personas= []
           let person = new Person("pessoa");
            person.setIdade(30);
            person.setTelefone("985149816");

            let estudante = new Estudante("estudante");
            estudante.setIdade(30);
            estudante.setTelefone("985149816");        
            estudante.setEscolaridade("superior completo");
        personas[0] =person;
        personas[1]=estudante;
        return null;    

    }



}