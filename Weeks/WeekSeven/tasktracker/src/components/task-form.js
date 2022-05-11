import { useState } from "react";

export default function TaskForm(props = { "addTask": () => { } }) {
    const newTask = { "id": "", "desc": "", "isCompleted": false };
    const [desc, setDesc] = useState("");

    function saveTaskDesc(event) {
        setDesc(event.target.value);
    }
    function saveTask(event) {
        newTask.id = Math.random() * 1000;
        newTask.desc = desc;
        props.addTask(newTask);
    }
    return (
        <>
            <input type="text" placeholder="Clean Sheets" onInput={saveTaskDesc} />
            <button onClick={saveTask}>Add Task</button>
        </>
    );
}