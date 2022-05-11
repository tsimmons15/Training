import { useSelector } from 'react-redux';
import './history';

export default function History() {
    const historyList = useSelector(s => s.history);

    return (
        <>
            <ul className="history">
                {
                    historyList.map(h =>
                        <li className='historyItem'>
                            {h}
                        </li>
                    )
                }
            </ul>
        </>
    );
}