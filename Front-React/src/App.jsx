import React, { useState } from 'react'
import Backround from './Component/Backround/Backround';
import Navbar from './Component/Navbar/Navbar';
import Hero from './Component/Hero/Hero';
import { useEffect } from 'react';
const App = () => {

  let heroData = [
    {text1 : "Dive into" , text2 : "what you love"},
    {text1 : "Indulge" , text2 : "your passions"},
    {text1 : "Givee in to" , text2 : "your passions"},
  ]
  const [heroCount , setHeroCount] = useState(0);
  const [playStatus , setPlayStatus] = useState(false);

  useEffect(()=>{
    setInterval(() => {
      setHeroCount((count)=>{return count===2?0 : count+1})
    }, 2200); 
  },[])
  return (
      <>
        <Backround playStatus = {playStatus} heroCount = {heroCount}/>
        <Navbar />
        <Hero setPlayStatus = {setPlayStatus}
              heroData = {heroData[heroCount]} 
              heroCount = {heroCount}
              setHeroCount = {setHeroCount}
              playStatus = {playStatus}
        />
        {/* <Header /> */}
        {/* <HeroSection /> */}
      </>
  )
}
export default App



/* 

import HeroSection from './HomePage/HeroSection/HeroSection'
import Header from './HomePage/Header/Header'
import React, { Component } from 'react'

class App extends Component {
  constructor(){
    super();
    this.state = {
          number : 0
    }
    this.eventHandler = this.eventHandler.bind(this);
  }

  eventHandler(){
      this.setState({
        number : this.state.number + 1
      })
  }
  render(){
    return (
            <div>
                  <h1>{this.state.number}</h1>
                  <button onClick={this.eventHandler}>Up number</button>
            </div>
      )
  }
}

export default App

 */