"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    }
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
var Person_1 = require("./Person");
/* usando heranca que é bem como java mesmo com extends e tal */
var Estudante = /** @class */ (function (_super) {
    __extends(Estudante, _super);
    /* crio o construtor e que eu obrigatoriamente tenho que chaamr o super aqui  */
    function Estudante(name) {
        return _super.call(this, name) || this;
    }
    Estudante.prototype.setEscolaridade = function (escolaridade) { this.escolaridade = escolaridade; };
    Estudante.prototype.getEscolaridade = function () { return this.escolaridade; };
    Estudante.prototype.ToString = function () {
        return "super.ToString() \n            a escolaridade e " + this.escolaridade + "        \n            ";
    };
    return Estudante;
}(Person_1.Person));
exports.Estudante = Estudante;
//# sourceMappingURL=Estudante.js.map