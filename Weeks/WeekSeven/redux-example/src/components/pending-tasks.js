import { useDispatch, useSelector } from "react-redux";

export default function PendingTasks(props = {}) {
    const onDispatch = useDispatch();
    const tasks = useSelector(s => s.tasks).filter(t => !t.isComplete);


    function updateTask(id) {
        onDispatch({ type: "tasks/updateTask", payload: id, newValue: true });
    }

    return (
        <>
            <h3>Pending Tasks</h3>
            <ul>
                {
                    tasks.map(t =>
                        <li key={t.id}>
                            {t.desc}
                            <button onClick={() => updateTask(t.id)}>Mark Complete</button>
                        </li>
                    )
                }
            </ul>
        </>
    );
}