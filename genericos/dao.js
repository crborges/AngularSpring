"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Dao = /** @class */ (function () {
    function Dao() {
    }
    Dao.prototype.insert = function (object) {
        console.log("comitado novo objeto no banco");
        return true;
    };
    Dao.prototype.update = function (object) {
        console.log("comitado objeto atualizado no banco");
        return true;
    };
    Dao.prototype.delete = function (id) {
        console.log("deletado logicamente objeto no banco");
        return true;
    };
    Dao.prototype.find = function (id) {
        console.log("retornando um ojeto especifico do banco");
        return null;
    };
    Dao.prototype.findAll = function () {
        console.log("retornando todos os objetos de um determinado tipo");
        return null;
    };
    return Dao;
}());
exports.Dao = Dao;
//# sourceMappingURL=dao.js.map