const arr = [];

arr.push("Hello");
arr.push(1);
arr.push({});
const two = arr.flat(["1", [2, [3, [5]]], 3.4], 100);


console.log(arr);
console.log(two);

console.log("Traditional for-loop");
for (let i = 0; i < arr.length; i++) {
    console.log(arr[i]);
}

console.log("For in?");
// For *key* in arr
for (const i in arr) {
    console.log(`${i} -> ${arr[i]}`);
}

console.log("For of, foreach");
for (const i of arr) {
    console.log(i);
}

console.log("Lambda forEach");
arr.forEach(i => console.log(i));

const bonjour = () => `${arr.map(v => v).reduce((p, c) => `${p} ${c}`)}`;
console.log(bonjour());
const ruojnob = () => `${arr.map(v => v).reduce((p, c) => `${c} ${p}`)}`;
console.log(ruojnob());
