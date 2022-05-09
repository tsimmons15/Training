export default function AlienGreeter() {
    function elcorGreet(event) {
        console.log(event);
        alert("With Much Happiness: Greetings.");
    }

    function hannarGreet(event) {
        alert("By the Enkindlers, this one is pleased to meet you.");
    }

    function gethGreet() {
        alert("We have come to the consesus that you are welcomed.");
    }

    return (
        <>
            <h3>Alien Greeter</h3>
            <p>Press a button to get a greeting!</p>
            <button onClick={hannarGreet}>Hannar Greeting</button>
            <button onClick={elcorGreet}>Elcor Greeting</button>
            <button onClick={gethGreet}>Geth Greeting</button>
        </>
    );
}