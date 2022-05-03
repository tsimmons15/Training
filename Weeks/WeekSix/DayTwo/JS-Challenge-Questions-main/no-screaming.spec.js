// A string is considered "screaming" if 50% or more of its characters are capitalized
// noScreams is a function that should take in an array of strings and return an array with all screams removed

// FORBIDDEN KEYWORDS
// for, while

function noScreams(ray) {
    return ray.filter(r => count(r, 0) > Math.floor(r.length / 2));
}

function count(ray, pos) {
    if (pos === ray.length) {
        return 0;
    }
    const char = ray.charAt(pos);
    return ((char >= 'a' && char <= 'z') ? 1 : 0) + count(ray, pos + 1);
}

test("All Screaming", () => {
    const result = noScreams(["AAAA", "BBBB", "CCCC"]);
    expect(result.length).toBe(0);
});

test("None Screaming", () => {
    const result = noScreams(["aaaa", "bbbb", "cccc"]);
    expect(result.length).toBe(3);
});

test("Some Screaming", () => {
    const result = noScreams(["aaaA", "BBBb", "cccC"]);
    expect(result.length).toBe(2);
}); 