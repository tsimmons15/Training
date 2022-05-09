import GamesTableItem from "./games-table-item";

export default function GamesTable() {
    const games = [
        { "title": "Mass Effect", "released": "2007" },
        { "title": "Mass Effect 2", "released": "2010" },
        { "title": "Mass Effect 3", "released": "2012" },
        { "title": "Mass Effect Legendary Edition", "released": "2021" }
    ];

    return (
        <>
            <table>
                <thead>
                    <tr>
                        <th>Game</th><th>Released</th>
                    </tr>
                </thead>
                <tbody>
                    {games.map(g => <GamesTableItem title={g.title} released={g.released} />)}
                </tbody>
            </table>
        </>
    );
}