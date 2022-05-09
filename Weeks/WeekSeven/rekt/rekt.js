const content = document.getElementById("content");

// a 'header' component
function Intro() {
    return `<h1>
                Welcome to the Office!
            </h1>`;
}

function About() {
    return `<p>
                The Office is located in <b>A Place</b>. The office
                has been working since about 1904.
            </p>`;
}

function EmployeeInfo(fname, lname, title) {
    return `<div>
                <h5>${fname}</h5>
                <h5>${lname}</h5>
                <h5>${title}</h5>
            </div>`;
}

function curriculaList(curricula = [""]) {
    return `<ul>
                ${curricula.map(c => `<li>${c}</li>`).join("")}
            </ul>`;
}

content.innerHTML =
    `
        ${Intro()}<br/>
        ${About()} <br />
        ${EmployeeInfo('Timothy', 'Simmons', 'Associate')}
        ${EmployeeInfo('Shelby', 'Lloyd', 'Associate Mentor')}
        ${EmployeeInfo('Adam', 'Ranieri', 'Principle Trainer')}
        ${EmployeeInfo('Ryan', 'Schlientz', 'Lead Trainer')}
        ${curriculaList(['Java', 'SpringBoot', 'React', 'ReactNative', 'JavaScript', 'NodeJS', 'AngularJS'])}
    `;