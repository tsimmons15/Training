const DEBUG = false;

console.log('Hello, World!');

if (DEBUG !== false) {
    alert('Annoying Popup!');
}

const name = "Adam";
const age = 19;
const nothing = null;
const wat = undefined; // default value if no assignment

function hello() {
    console.log("hello");
}

altHello = function () {
    console.log("Another hello");
};

hello();
altHello();

namedHello = (name) => {
    console.log("Hola, " + name);
};
namedHello("Steven");
namedHello();
namedHello("One", "Two", "Three");
namedHello(["One", "Two", "Three"]);

function intro() {
    let introduction = "Welcome";
    var otherIntro = " to";
    const constIntro = " my";
    defaultIntro = " program!";
    console.log(introduction + otherIntro + constIntro + defaultIntro);
}
intro();
console.log(defaultIntro); // "default" type is global
//console.log(constIntro); // const is unchangeable local, default choice
//console.log(otherIntro); // var is *function* scope, not local
//console.log(introduction); // let is local, if not const

function varTest() {
    if (true) {
        var otherVariable = "other variable";
        let variable = "a variable";
        console.log(variable);
    }

    console.log(otherVariable);
    //console.log(variable);
}

varTest();

function hoisted() {
    console.log(x);
    var x = 100;
    console.log(x);
}

hoisted();

// template literals
const fname = "Adam";
const lname = "Ranieri";
const greeting = `Hello, ${fname} ${lname}`;
console.log(greeting);

// objects / class.prototype?
const obj = {
    "attr1": "value",
    "attr2": "value",
    "attr3": "value",
    "func": () => {
        console.log('Why, hello there, ' + this.name + '!');
        // Demonstrating the issue with arrow functions vs. function() keyword.
    },
    "thing": function () {
        console.log(`Why, hello there, ${this.name}!`);
    },
    "name": "Timothy"
}; // JSON - JavaScript Object Notation

console.log(obj);

obj.attr1 = "value2";

console.log(obj);
obj.func();
obj.thing();
delete obj.attr3;
console.log(obj);
obj.attr2 = undefined;
console.log(obj);
obj.attr2 = null;
console.log(obj);