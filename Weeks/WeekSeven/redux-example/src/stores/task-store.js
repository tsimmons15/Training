import { configureStore, createSlice } from "@reduxjs/toolkit";

const initialState = {
    tasks: [
        { id: 100, desc: "Thing", isComplete: false },
        { id: 101, desc: "Completed Thing", isComplete: true },
    ]
};
const taskSlice = createSlice({
    name: "tasks",
    initialState,
    reducers: {
        createTask(state, action) {
            state.tasks.push(action.payload);
        },
        updateTask(state, action) {
            const id = action.payload;
            state.tasks.forEach(t => {
                if (t.id === id) {
                    t.isComplete = action.newValue;
                }
            });
        }
    }
});

export const taskStore = configureStore({
    reducer: taskSlice.reducer
});