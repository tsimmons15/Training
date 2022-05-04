document.getElementById("historyList").innerHTML = "";
document.getElementById("expression").value = "";
document.getElementById("display").value = "";


const history = [];
const operands = [undefined, undefined, undefined];
let entry = "";

function handleEvaluate(event) {
    if (entry !== "") {
        operands[1] = entry;
    }

    const expression = renderExpression();

    switch (operands[2]) {
        case "+":
            entry = operands[0] + operands[1];
            break;
        case "-":
            entry = operands[0] - operands[1];
            break;
        case "*":
            entry = operands[0] * operands[1];
            break;
        case "/":
            entry = operands[0] / operands[1];
            break;
    }

    operands[0] = entry;
    renderDisplay(entry);
    entry = "";

    console.log(`New history: ${expression}, ${entry}`);
    history.push({ "expression": expression, "value": entry });
    renderHistory();
}

function renderHistory() {
    const historyList = document.getElementById("historyList");
    historyList.innerHTML = history.map(h => `<li>${h.expression}</li>`).join("");
}

function handleOperator(event, op) {
    if (isUnaryOp(op)) {
        console.log("unary: " + op);
        handleUnary(op);
        return;
    }

    operands[0] = entry;
    operands[2] = op;
    console.log(`${operands[0]} ${operands[2]}`);
    entry = "";
    renderExpression();
}

function isUnaryOp(op) {
    return op === "sqrt" || op === "%" ||
        op === "sq" || op === "inv" || op === "+-";
}

function handleUnary(op) {
    switch (op) {
        case "sq":
            entry = Math.pow(entry, 2);
            break;
        case "sqrt":
            entry = Math.sqrt(entry);
            break;
        case "inv":
            if (entry !== 0) {
                entry = 1 / entry;
            }
            break;
        case "+-":
            entry *= -1;
            break;
    }

    renderDisplay(entry);
}

function renderExpression(isClear = false) {
    const expr = document.getElementById("expression");

    if (isClear) {
        expr.value = "";
        return;
    }

    let expression = "";

    // May need to work out a better way to check, given 0 is valid...
    if (operands[0]) {
        expression += `${operands[0]}`;
    }

    if (operands[2]) {
        expression += ` ${operands[2]} `;
    }

    if (operands[1]) {
        expression += `${operands[1]}`;
    }

    expr.value = expression;

    return expression;
}

function renderDisplay(value) {
    const display = document.getElementById("display");
    display.value = value;
}

function handleOperand(event, operand) {
    const newOperand = parseFloat(entry + operand);
    entry = newOperand;

    renderDisplay(entry);
}

function handleMem(event, op) {

}

function handleClear(event, op) {
    if (op === "clear") {
        operands[0] = operands[1] = operands[2] = "";
    } else {
        operands[0] = 0;
    }

    renderDisplay("");
    renderExpression(isClear = true);
}