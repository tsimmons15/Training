// write a function that takes in an array and reverses each word in the array then the array

function ultraReverse(ray) {
    return ray.map(r => [...r].reduce((p, c) => c + p)).reduce((p, c) => [c].concat(p));
}

test("Super Reverse", () => {
    let words = ["abc", "def", "xyz"];
    words = ultraReverse(words);
    expect(words).toEqual(["zyx", "fed", "cba"]);
});