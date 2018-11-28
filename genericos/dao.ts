import { DaoInterface } from './dao.interface';

export class Dao<T> implements DaoInterface<T>{
    tableName: string;   
    
    insert(object: T): boolean {
        console.log(`comitado novo objeto no banco`);
        return true;
    }
    
    update(object: T): boolean {
        console.log(`comitado objeto atualizado no banco`);
        return true;
    }
    
    delete(id: number): boolean {
        console.log(`deletado logicamente objeto no banco`);
        return true;
    }
    
    find(id: number)  :T{
        console.log(`retornando um ojeto especifico do banco`);
        return null;
    }
   
    findAll(): [T] {
        console.log(`retornando todos os objetos de um determinado tipo`);
        return null;
    }
    
}