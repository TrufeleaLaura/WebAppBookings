import React from 'react';
import ReactDOM from 'react-dom/client';
//import { createRoot } from 'react-dom';
import {createRoot} from 'react-dom/client';
import './index.css';
import App from './App';

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);



root.render(
  //<React.StrictMode>
   // <BrowserRouter>
     // <AuthProvider>
            <App />
      //</AuthProvider>
    //</BrowserRouter>
    
  //</React.StrictMode>
);



