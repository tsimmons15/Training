// default args
function defaults(name = "nobody") {
    console.log(`Hello, ${name}!`);
}

defaults();
defaults("Testing");

/**
 * Adds two arguments together.
 * @param {number} num1 default 0.
 * @param {number} num2 default 0.
 * @returns sum of the arguments
 */
function add(num1 = 0, num2 = 0) {
    return num1 + num2;
}

console.log(add(1));
console.log(add());
console.log(add(1, 2));

// VERSUS

function addNoDefault(num1, num2) {
    return num1 + num2;
}

console.log(addNoDefault());
console.log(addNoDefault(1));

// Array destructuring
const temps = [71, 73, 65, 91, 10, 5, 200];
const [t1, t2, ...other] = temps;

console.log(t1, t2);
console.log(other);

function analyze(numbers = [0]) {
    let total = 0;
    let min = numbers[0];
    let max = numbers[0];
    for (const n of numbers) {
        total += n;
        if (min > n) {
            min = n;
        }
        if (max < n) {
            max = n;
        }
    }

    let avg = total / numbers.length;

    return [total, min, max, avg];
}

const [total, min, max, avg] = analyze(temps);
console.log(total, min, max, avg);

const adam = {
    "firstname": "adam",
    "lastname": "ranieri",
    "age": 19,
    "isTrainer": true,
    toString() {
        return `${(this.isTrainer ? "Trainer " : "")}${this.lastname}, ${this.firstname}. ${this.age} years old.`;
    }
};

// name based unlike in array destructuring
const { age } = adam;
console.log(age);
console.log(adam.toString());

const owner = "adam";
const name = "Rover";
const pet_age = 7;

// You can just provide a named variable, it'll assume the
// attribute name is the variable name.
const doggo = { owner, name, "age": pet_age };
console.log(doggo);
console.log(doggo.owner);

// spread operator
const newTemps = [temps, 14, 122, 10];
console.log(newTemps);
// VERSUS
// flattens array before adding
const actualNewTemps = [...temps, 14, 122, 10];
console.log(actualNewTemps);

// Garda

const result = true || false;
console.log(result);

function printList(list) {
    return (list || []).map(i => `${i}`).join("");
}

console.log("printing lists");
console.log("Null list: " + printList(null));
console.log("Filled list: " + printList([1, 2, 3, 4, 5, 6]));
