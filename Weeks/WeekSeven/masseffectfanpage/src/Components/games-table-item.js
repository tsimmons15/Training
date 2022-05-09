export default function GamesTableItem(props) {
    return (
        <tr key='{props.title}'>
            <td>{props.title}</td><td>{props.released}</td>
        </tr>
    );
}