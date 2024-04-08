import React from 'react'
import ReactDOM from 'react-dom/client'//c'est la page de navigateur
import './index.css'
/* import './HomePage/Header/Header.css' */
import About from './Component/Navbar/NavbarAbout/About.jsx'
import HeroSection from './Component/HeroSection/HeroSection.jsx'
/* ReactDOM.createRoot(document.getElementById('root')).render(

  <React.StrictMode>
    <App />
  </React.StrictMode>
)  */
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import App from './App.jsx'


const router = createBrowserRouter([
  {
    path: "/",
    element: <HeroSection />
  },
  {
    path: "/About",
    element: <About/>
  },
  {
    path: "homePage",
    element: <App />
  },
]);
ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
/* ReactDOM.createRoot(document.getElementById('root')).render(<App />) */


