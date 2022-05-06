import chalk from "chalk";
import figlet from "figlet";

const message = "Hello!";
console.log(chalk.red.bold.underline(message));

figlet(message, (err, result) => {
    if (err) {
        console.log('failed');
        return;
    }
    console.log(chalk.magenta(result));
});