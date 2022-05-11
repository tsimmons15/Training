import React from 'react';
import ReactDOM from 'react-dom/client';
import TaskManager from './components/task-manager';
import './index.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <TaskManager />
  </React.StrictMode>
);
