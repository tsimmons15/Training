import { useState } from "react";
import PokeViewer from "./poke-viewer";
import axios from "axios";

export default function MainPage() {
    const [searchInput, setSearchInput] = useState();
    const [pokemon, setPokemon] = useState({});

    async function fetchPokemon(event) {
        const response = await axios.get(`https://pokeapi.co/api/v2/pokemon/${searchInput.name}`);
        const fetchedPokemon = response.data;
        console.log(fetchedPokemon);
        setPokemon(fetchedPokemon);
    }

    function updatePokename(event) {
        setSearchInput({ "name": event.target.value });
    }

    return (
        <>
            <h1>Pokedex</h1>
            <input type="text" onChange={updatePokename} />
            <button onClick={fetchPokemon}>Get Pokemon</button>
            {
                pokemon.name && <PokeViewer name={pokemon.name} height={pokemon.height} weight={pokemon.weight} spriteUrl={pokemon.sprites.front_default} id={pokemon.id} />
            }
        </>
    );
};