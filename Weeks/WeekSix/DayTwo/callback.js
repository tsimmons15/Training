function hello() {
    console.log("Hello");
}

function hola() {
    console.log("Hola");
}

function doTwice(func) {
    func();
    func();
}

doTwice(hello);
doTwice(hola);

const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

const mean = nums.reduce((p, c) => p + c) / nums.length;
const variance = nums.map(i => Math.pow(i - mean, 2)).
    reduce((p, c) => p + c) / (nums.length - 1);
console.log(variance);

const stuff = ["Tim", "Kim", undefined, "Mike", false, undefined, null, "Jason", null, false, NaN, null, false, undefined, NaN, null, "Matt", "Harvey"];
const noNulls = stuff.filter(i => i !== null);
//              stuff.filter(i=>i) if we just want to remove falsy values
//              stuff.filter(i => ![null, NaN, undefined].includes(i)); if you need to filter only some falsy values
console.log(noNulls);
console.log(noNulls.reduce((p, c) => `${p}, ${c}`));