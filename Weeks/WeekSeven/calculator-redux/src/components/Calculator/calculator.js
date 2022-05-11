import './calculator.css';

import { useState } from "react";
import { Provider } from "react-redux";

import Display from "../Display/display";
import NumPad from "../NumPad/num-pad";
import History from "../History/history";
import { calculationStore } from '../../stores/calculator-store';


export default function Calculator() {
    return (
        <>
            <h1>Calculator</h1>
            <Provider store={calculationStore}>
                <div className="container">
                    <History />
                    <Display />
                    <NumPad />
                </div>
            </Provider>
        </>
    );
}