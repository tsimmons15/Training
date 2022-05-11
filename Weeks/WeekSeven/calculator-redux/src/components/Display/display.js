import './display.css';

import { useSelector } from 'react-redux';

export default function Display() {
    const display = useSelector(s => s.display);
    return (
        <>
            <div className="display">
                <p className="right-align">{display.equation + (display.isRenderable ? (' = ' + eval(display.equation)) : "")}</p>
            </div>
        </>
    );
}