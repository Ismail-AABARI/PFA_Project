import React from 'react'
import './Navbar.css'
import { Link } from 'react-router-dom';
const Navbar = () => {
  return (
    <div className='nav'>
        <div className='nav-logo'>EV-olution</div>
        <ul className='nav-menu'>
          <li>Home</li>
          <li>Explore</li>
          <li>Login</li>
          <li><Link to="/About" className='nav-button style fade-in'>About</Link></li>
          <li className='nav-contact'>Contact</li>
        </ul>
    </div>
    
  );
}

export default Navbar;
