/* UMA INTERNFACE DE UM DAO GENERICO
Essa interface rfecebe um ohjeto qq um <T> 
Tem os metodos basicos de um CRUD
Ele receber qq cvoisa e trata
*/
export interface DaoInterface<T>{
    
    tableName: string;
    insert(object: T) : boolean;
    update(object: T) : boolean;
    delete(id: number) : boolean;
    find(id: number) : T;
    findAll() : [T];

    

}