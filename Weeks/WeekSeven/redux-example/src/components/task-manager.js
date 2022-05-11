import { useState } from "react";
import { Provider } from "react-redux";

import CompletedTasks from "./completed-tasks";
import PendingTasks from "./pending-tasks";
import TaskForm from "./task-form";
import { taskStore } from "../stores/task-store";


export default function TaskManager() {
    const [tasks, setTasks] = useState([]);

    function updateTask(task) {

    }

    function addTask(task) {

    }

    return (
        <>
            <h1>Task Manager</h1>
            <Provider store={taskStore}>
                <TaskForm />
                <PendingTasks />
                <CompletedTasks />
            </Provider>
        </>
    );
}