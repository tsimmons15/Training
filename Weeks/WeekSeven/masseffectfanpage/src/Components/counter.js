import { useState } from 'react';

export default function Counter() {
    let val = { num: 0 };

    function increment() {
        const newValue = { ...value };
        newValue.num = value.num + 1;
        setValue(newValue);
    }

    function decrement() {
        const newValue = { ...value };
        newValue.num = value.num - 1;
        setValue(newValue);
    }

    const [value, setValue] = useState({ "num": 0 });
    return (
        <>
            <h3>The Value is: {value.num}</h3>
            <button onClick={increment}>Increment</button>
            <button onClick={decrement}>Decrement</button>
        </>
    );
}