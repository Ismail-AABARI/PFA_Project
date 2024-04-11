import './App.css'


import React, { useState, useEffect } from 'react';

const AnnouncementBar = () => {
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    // Simulating the announcement bar animation from left to right
    const timer = setTimeout(() => {
      setIsOpen(true);
    }, 1000); // Adjust timing as needed

    return () => clearTimeout(timer);
  }, []);

  return (
    <div className={`bg-black text-white py-0.1 px-4 fixed top-0 left-0 right-0 z-50 transition-transform transform ${isOpen ? 'translate-x-0' : '-translate-x-full'}`}>
      <marquee className="text-center text-white">Free Shipping on Orders Over $50!</marquee>
    </div>
  );
};



export default AnnouncementBar;

