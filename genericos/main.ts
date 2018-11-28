import { DaoInterface } from './dao.interface';
import { Person } from './../heranca/Person';
import { Dao } from './dao';

let dao: DaoInterface<Person> = new Dao<Person>();
let person: Person = new Person('Cristiano');
dao.insert(person);
