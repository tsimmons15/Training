export default function PendingTasks(props = {}) {
    function updateTask(task) {
        task.isComplete = true;
        props.onUpdateTask(task);
    }

    return (
        <>
            <h3>Pending Tasks</h3>
            <ul>
                {props.pending.map(t => <li key={t.id}>{t.desc} <button onClick={() => updateTask(t)}> Mark Complete</button ></li >)}
            </ul >
        </>
    );
}