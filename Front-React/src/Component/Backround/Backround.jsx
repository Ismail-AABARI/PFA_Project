import React from 'react'
import Cars from '../../assets/car3.jpg'
import Cars_second from '../../assets/car2.jpg'
import speedCars from '../../assets/SpeedCars.jpg'
import video from '../../assets/videoCars.mp4'
import './Backround.css'
const Backround = ({playStatus , heroCount}) => {
    if (playStatus) {
        return(
            <video className='backround fade-in' autoPlay loop muted>
                <source src={video} type='video/mp4' />
            </video>
        )
    }
    else if(heroCount==0){
        return(
            <img src={Cars} className='backround fade-in' alt=""  />
        )
    }
    else if(heroCount==1){
        return(
            <img src={Cars_second} className='backround fade-in' alt=""  />
        )
    }
    else if(heroCount==2){
        return(
            <img src={speedCars} className='backround fade-in' alt=""  />
        )
    }
}

export default Backround