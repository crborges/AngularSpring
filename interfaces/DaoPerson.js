"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Person_1 = require("./../heranca/Person");
var Estudante_1 = require("../heranca/Estudante");
var DaoPerson = /** @class */ (function () {
    function DaoPerson() {
    }
    DaoPerson.prototype.insert = function (person) {
        if (person.getIdade() < 0) {
            throw new Error("Pessoa invalida");
        }
        console.log("comitada a pessoa " + person.getNome() + " no banco");
        return true;
    };
    DaoPerson.prototype.update = function (person) {
        if (person.getIdade() < 0) {
            throw new Error("Pessoa invalida");
        }
        console.log("atualizada a pessoa " + person.getNome() + " no banco");
        return true;
    };
    DaoPerson.prototype.delete = function (id) {
        var person = this.find(id);
        if (id < 0) {
            throw new Error("Pessoa invalida");
        }
        console.log("deletada a pessoa no banco");
        return true;
    };
    DaoPerson.prototype.find = function (id) {
        if (id > 3) {
            throw new Error("Pessoa invalida");
        }
        if (id == 1) {
            var person = new Person_1.Person("pessoa");
            person.setIdade(30);
            person.setTelefone("985149816");
            return person.ToString();
        }
        if (id == 2) {
            var estudante = new Estudante_1.Estudante("estudante");
            estudante.setIdade(30);
            estudante.setTelefone("985149816");
            estudante.setEscolaridade("superior completo");
            return estudante;
        }
    };
    DaoPerson.prototype.findAll = function () {
        var personas = [];
        var person = new Person_1.Person("pessoa");
        person.setIdade(30);
        person.setTelefone("985149816");
        var estudante = new Estudante_1.Estudante("estudante");
        estudante.setIdade(30);
        estudante.setTelefone("985149816");
        estudante.setEscolaridade("superior completo");
        personas[0] = person;
        personas[1] = estudante;
        return null;
    };
    return DaoPerson;
}());
exports.DaoPerson = DaoPerson;
//# sourceMappingURL=DaoPerson.js.map