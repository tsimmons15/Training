import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './num-pad.css';

export default function NumPad() {
    const onDispatch = useDispatch();
    const display = useSelector(s => s.display);

    const [expression, setExpression] = useState("");

    function equals() {
        onDispatch({ type: "calculator/equalsPressed", payload: expression });

        onDispatch({ type: "calculator/addToHistory", payload: `${expression} = ${eval(expression)}` });
        setExpression("");
    }

    function addToExpression(char) {
        console.log(expression + char);
        const exp = expression + char;
        setExpression(exp);
        onDispatch({ type: "calculator/addToEquation", payload: exp });
        onDispatch({ type: "calculator/unRenderable", payload: undefined });
    }

    function clearExp() {
        setExpression("");
    }

    return (
        <>
            <div className="numpad">
                <button className="pct">%</button>
                <button className="ce">CE</button>
                <button className="clear" onClick={clearExp}>C</button>
                <button className="backspace">backspace</button>
                <button className="inverse">inv</button>
                <button className="squared">squared</button>
                <button className="sqrt">sqrt</button>
                <button className="divide" onClick={() => addToExpression('/')}>/</button>
                <button className="num7" onClick={() => addToExpression('7')}>7</button>
                <button className="num8" onClick={() => addToExpression('8')}>8</button>
                <button className="num9" onClick={() => addToExpression('9')}>9</button>
                <button className="times" onClick={() => addToExpression('*')}>X</button>
                <button className="num4" onClick={() => addToExpression('4')}>4</button>
                <button className="num5" onClick={() => addToExpression('5')}>5</button>
                <button className="num6" onClick={() => addToExpression('6')}>6</button>
                <button className="subtract" onClick={() => addToExpression('-')}>-</button>
                <button className="num1" onClick={() => addToExpression('1')}>1</button>
                <button className="num2" onClick={() => addToExpression('2')}>2</button>
                <button className="num3" onClick={() => addToExpression('3')}>3</button>
                <button className="plus" onClick={() => addToExpression('+')}>+</button>
                <button className="plus-minus">+-</button>
                <button className="num0" onClick={() => addToExpression('0')}>0</button>
                <button className="radix" onClick={() => addToExpression('.')}>.</button>
                <button className="equals" onClick={() => equals()}>=</button>
            </div >
        </>
    );
}