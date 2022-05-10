import { useState } from "react";

export default function CharacterViewer() {
    const [teammate, setTeammate] = useState({ "name": "Garrus Vakarian", "species": "Turian" });

    function setGarrus() {
        setTeammate({ "name": "Garrus Vakarian", "species": "Turian" });
    }
    function setMordin() {
        setTeammate({ "name": "Mordin", "species": "Salarian" });
    }
    function setLiara() {
        setTeammate({ "name": "Liara", "species": "Asari" });
    }
    function setTali() {
        setTeammate({ "name": "Tali", "species": "Quarian" });
    }

    return (
        <>
            <h4>Character Viewer</h4>
            <button onClick={setGarrus}>Garrus</button>
            <button onClick={setMordin}>Morden</button>
            <button onClick={setLiara}>Liara</button>
            <button onClick={setTali}>Tali</button>
            <dl>
                <dt>Name</dt>
                <dd>
                    {teammate.name}
                </dd>
                <dt>Species</dt>
                <dd>
                    {teammate.species}
                </dd>
            </dl>
        </>
    );
}