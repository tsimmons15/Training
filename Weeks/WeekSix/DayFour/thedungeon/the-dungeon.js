const url = "https://www.dnd5eapi.co/api";

async function init() {
    queryClasses();
    queryRaces();
}

async function queryClasses() {
    const response = await fetch(`${url}/classes/`);
    const json = await response.json();
    document.getElementById("title").innerText = "Classes";
    const navList = document.getElementById("classList");
    navList.innerHTML = json.results.map(c => `<li class="clickable" onclick="renderClass('${c.index}')">${c.name}</li>`).join("");
    renderClass(json.results[0].index);
}


async function renderClass(index) {
    const infoRequest = await fetch(`${url}/classes/${index}/`);
    const classInfo = await infoRequest.json();
    const info = document.getElementById("classInfo");
    info.innerHTML = `<dt>${classInfo.name}</dt>
        <dt>Hit Die</dt>
        <dd>${classInfo.hit_die}</dd>
        <dt>Proficiencies</dt>
        ${classInfo.proficiencies.map(p => `<dd>${p.name}</dd>`).join("")}
        <dt>Choose ${classInfo.proficiency_choices[0].choose} proficiencies below</dt>
        ${classInfo.proficiency_choices[0].from.map(c => `<dd>${c.name}</dd>`).join("")}
        <dt>Saving Throws</dt>
        ${classInfo.saving_throws.map(st => `<dd>${st.name}</dd>`).join("")}
        <dt>Subclasses</dt>
        ${classInfo.subclasses.map(sc => `<dd>${sc.name}</dd>`)}`;

}


async function queryRaces() {
    const response = await fetch(`${url}/races/`);
    const json = await response.json();
    document.getElementById("title").innerText = "Classes";
    const navList = document.getElementById("raceList");
    navList.innerHTML = json.results.map(r => `<li class="clickable" onclick="renderRace('${r.index}')">${r.name}</li>`).join("");
    renderRace(json.results[0].index);
}

async function renderRace(index) {
    const response = await fetch(`${url}/races/${index}/`);
    const raceInfo = await response.json();
    const info = document.getElementById("raceInfo");
    info.innerHTML =
        `<dt>${raceInfo.name}</dt>
        <dt>Landspeed: ${raceInfo.speed}</dt>
        <dt>Ability Bonuses</dt>
        ${raceInfo.ability_bonuses.map(score => `<dd>${score.ability_score.name}: ${score.bonus}</dd>`).join("")}
        <dt>Alignment</dt>
        <dd>${raceInfo.alignment}</dd>
        <dt>Age</dt>
        <dd>${raceInfo.age}</dd>
        <dt>Size: ${raceInfo.size}</dt>
        <dd>${raceInfo.size_description}</dd>
        <dt>Starting Proficiencies</dt>
        ${(raceInfo.starting_proficiencies || []).map(sp => `<dd></dd>`).join('')}
        <dt>Language Proficiencies</dt>
        ${raceInfo.languages.map(l => `<dd>${l.name}</dd>`).join('')}
        <dt>Traits</dt>
        ${raceInfo.traits.map(t => `<dd>${t.name}</dd>`).join('')}`;
}


async function navigate(event, target) {
    event.stopPropagation();
    switch (target) {
        case "home":

            break;
        case "start":

            break;
        case "classes":
            queryClasses();
            break;
        case "races":

            break;
        case "items":

            break;
        case "spells":

            break;
    }
}