// write a function that takes in a number and gives back the output in USD
// with appropriate precision
// negative amounts should throw an error

function usd(amount = 0) {
    if (amount < 0) {
        throw RangeError("amount is negative: " + amount);
    }
    return amount.toLocaleString('en-US', {
        style: 'currency',
        currency: 'USD'
    });
}

test("100 dollars", () => {
    const result = usd(100);
    expect(result).toBe("$100.00");
});

test("91.3688 dollars", () => {
    const result = usd(91.3688);
    expect(result).toBe('$91.37');
});

