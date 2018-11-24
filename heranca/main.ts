import { DaoPerson } from './../interfaces/DaoPerson';
/*  um import bem java mesmo da classe person para usar */
import { Person } from './Person';
import { Estudante } from './Estudante';
/* da o new da classe person ate ai bem java tbem  */


let person = new  Person("Cristiano Rodrigues Borges");
person.setIdade(30);
person.setTelefone("985149816");
console.log(person.ToString());



let estudante = new Estudante("Cristiano b");
estudante.setIdade(30);
estudante.setTelefone("985149816");
estudante.setEscolaridade("Superior Completo");
console.log(estudante.ToString());


let daoPerson = new DaoPerson();
console.log(daoPerson.insert(person));
console.log(daoPerson.update(person));
console.log(daoPerson.delete(1));
console.log(daoPerson.find(2));
console.log(daoPerson.findAll());