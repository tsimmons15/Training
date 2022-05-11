import { useState } from "react";
import { useDispatch } from "react-redux";

export default function TaskForm() {
    const onDispatch = useDispatch();

    const [desc, setDesc] = useState("");

    function updateDesc(event) {
        setDesc(event.target.value);
    }

    function addTask() {
        const newTask = {
            id: Math.floor(Math.random() * 1000),
            desc,
            isComplete: false
        };

        console.log(newTask);
        onDispatch({ type: "tasks/createTask", payload: newTask });

    }

    return (
        <>
            <h3>Add New Task</h3>
            <input type="text" placeholder="New Task..." onInput={updateDesc} />
            <button onClick={addTask}>Add</button>
        </>
    );
}