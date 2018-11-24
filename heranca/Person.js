"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/* da o comando export do js para poder usar a classe fora ddaqui em outros pontos */
/* cria uma classe poj bem simplisinha de pessoa e tal */
var Person = /** @class */ (function () {
    /* um construtor bem, padrão tipo mais simples de java em que incia os parametros */
    function Person(nome) {
        this.nome = nome;
    }
    Person.prototype.setNome = function (nome) { this.nome = nome; };
    Person.prototype.setIdade = function (idade) { this.idade = idade; };
    Person.prototype.setTelefone = function (telefone) { this.telefone = telefone; };
    Person.prototype.getNome = function () { return this.nome; };
    Person.prototype.getIdade = function () { return this.idade; };
    Person.prototype.getTelefone = function () { return this.telefone; };
    /* um metodo bem to string que vai msotrar os dados do cara ou até mesmo um get de algo
     */
    Person.prototype.ToString = function () {
        /* para iterar variaveis dentro do codigo com o this use as crases ´para msotrar */
        return " o nome eh " + this.nome + " \n                a idade eh " + this.idade + "\n                o telefone eh " + this.telefone;
    };
    Person.prototype.Equals = function (Person) { return this === Person; };
    return Person;
}());
exports.Person = Person;
//# sourceMappingURL=Person.js.map