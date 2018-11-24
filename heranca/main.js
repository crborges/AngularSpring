"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var DaoPerson_1 = require("./../interfaces/DaoPerson");
/*  um import bem java mesmo da classe person para usar */
var Person_1 = require("./Person");
var Estudante_1 = require("./Estudante");
/* da o new da classe person ate ai bem java tbem  */
var person = new Person_1.Person("Cristiano Rodrigues Borges");
person.setIdade(30);
person.setTelefone("985149816");
console.log(person.ToString());
var estudante = new Estudante_1.Estudante("Cristiano b");
estudante.setIdade(30);
estudante.setTelefone("985149816");
estudante.setEscolaridade("Superior Completo");
console.log(estudante.ToString());
var daoPerson = new DaoPerson_1.DaoPerson();
console.log(daoPerson.insert(person));
console.log(daoPerson.update(person));
console.log(daoPerson.delete(1));
console.log(daoPerson.find(2));
console.log(daoPerson.findAll());
//# sourceMappingURL=main.js.map