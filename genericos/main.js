"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Person_1 = require("./../heranca/Person");
var dao_1 = require("./dao");
var dao = new dao_1.Dao();
var person = new Person_1.Person('Cristiano');
dao.insert(person);
//# sourceMappingURL=main.js.map