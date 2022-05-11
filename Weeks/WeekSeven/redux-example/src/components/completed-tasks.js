import { useDispatch, useSelector } from "react-redux";

export default function CompletedTasks() {
    const tasks = useSelector(s => s.tasks).filter(t => t.isComplete);
    const onDispatch = useDispatch();

    function updateTask(id) {
        onDispatch({ type: "tasks/updateTask", payload: id, newValue: false });
    }

    return (
        <>
            <h3>Completed Tasks</h3>
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