export default function PokeViewer(props = { name: "", height: 0, weight: 0, spriteUrl: "", id: 0 }) {
    const { name, height, weight, spriteUrl, id } = props;

    return (
        <>
            <dl key={id}>
                <dt>Name</dt>
                <dd>{name}</dd>
                <dt>Height</dt>
                <dd>{height}</dd>
                <dt>Weight</dt>
                <dd>{weight}</dd>
                <dt>Sprite</dt>
                <dd><img src={spriteUrl} alt="{name}'s sprite" /></dd>
            </dl>
        </>
    );
}