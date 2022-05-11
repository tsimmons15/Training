export default function CompletedTasks(props = { "completed": [{}] }) {
    function updateTask(task) {
        task.isComplete = false;
        props.onUpdateTask(task);
    }

    return (
        <>
            <h3>Completed Tasks</h3>
            <ul>
                {props.completed.map(t => <li key={t.id}>{t.desc} <button onClick={() => updateTask(t)}>Mark Uncomplete</button></li>)}
            </ul>
        </>
    );
}