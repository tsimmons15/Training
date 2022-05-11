import { useState } from "react";
import CompletedTasks from "./completed-tasks";
import PendingTasks from "./pending-tasks";
import TaskForm from "./task-form";

export default function TaskManager() {
    const [tasks, setTasks] = useState([]);

    function addTask(task) {
        const cloned = [...tasks];
        cloned.push(task);
        setTasks(cloned);
    }

    function updateTask(task) {
        const index = tasks.indexOf(task);
        const cloned = [...tasks];
        index >= 0 && (cloned[index] = task);
        setTasks(cloned);
    }

    return (
        <>
            <h1>Task Tracker</h1>
            <TaskForm addTask={addTask} />
            <PendingTasks pending={tasks.filter(t => !t.isComplete)} onUpdateTask={updateTask} />
            <CompletedTasks completed={tasks.filter(t => t.isComplete)} onUpdateTask={updateTask} />
        </>
    );
}