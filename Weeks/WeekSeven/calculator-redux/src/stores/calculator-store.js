import { configureStore, createSlice } from "@reduxjs/toolkit";


const initialState = {
    history: [],
    display: {
        equation: "",
        isRenderable: false
    }
};

const calculationSlice = createSlice({
    name: "calculator",
    initialState,
    reducers: {
        addToHistory(state, action) {
            state.history.push(action.payload);
            console.log(state.history);
        },
        equalsPressed(state, action) {
            state.display.equation = action.payload;
            state.display.isRenderable = true;
            console.log(state.isRenderable);
        },
        addToEquation(state, action) {
            state.display.equation = action.payload;
        },
        unRenderable(state, action) {
            state.display.isRenderable = false;
        }
    }
});

export const calculationStore = configureStore({
    reducer: calculationSlice.reducer
});