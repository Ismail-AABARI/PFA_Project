import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import './HeroSection.css'

const HeroSection = () => {
  return (
    <div className="hero-container">
      <video autoPlay loop muted className="video-bg">
        {/* <source src="src/backround-video.mp4" type="video/mp4" /> */}
        {/* <source src="src/Formation/Background.mp4" type="video/mp4" /> */}
        {/* <source src="src/Video.mp4" type="video/mp4" /> */}
        <source src="videos/paysage.mp4" type="video/mp4" /> 
        Your browser does not support the video tag.
      </video>
      <div className="hero-content">
        <h1 className="hero-heading">Welcome to Our Website</h1>
        <p className="hero-subheading">Discover amazing products and services</p>
        {/* <boutt className='hero-button ' to={'/login'}>Explore now</Link> */}
        <Link to="/homePage" className="link-style hero-button" >Explore Now</Link>
      </div>
    </div>
  );
}   

export default HeroSection;
