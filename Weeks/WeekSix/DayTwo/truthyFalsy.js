console.log(9 == "9");   //Truthy
console.log(9 === "9");  //Falsy
console.log(true == 1);  //Truthy
console.log(true === 1); //Falsy
console.log(10 * false == "0"); //Truthy
console.log(10 * false === "0"); //Falsy
console.log(10 * false == ""); //Truthy
console.log(10 * false === "");
console.log(true == "true"); //Falsy
console.log("true" == true);
console.log(10 / 0 == 100 / 0); // Truthy, Number.Infinity == Number.Infinity
console.log(NaN == NaN); // Falsy
console.log(typeof NaN); // number

// All Falsy values:
console.log(Boolean("")); // empty string is falsy
console.log(Boolean(0)); // 0 is falsy
console.log(Boolean(false)); // obviously...
console.log(Boolean(null));
console.log(Boolean(undefined));
console.log(Boolean(NaN));