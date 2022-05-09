import React from 'react';
import ReactDOM from 'react-dom/client';
import Intro from './Components/intro';
import About from './Components/about';
import ContactUs from './Components/contact-us';
import GamesTable from './Components/games-table';
import AlienGreeter from './Components/alien-greeter';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <h1>Welcome to barebones React</h1>
    <Intro />
    <About />
    <GamesTable />
    <AlienGreeter />
    <ContactUs email='test@gmail.com' phone='4444444444' addr='1234 fake str.' />
  </React.StrictMode>
);