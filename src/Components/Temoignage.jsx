import React from 'react';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import react from "../assets/react.svg";
import react1 from "../assets/stars.gif";




const data = [
  {
    name: "John Doe",
    image: react,
    review: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
  },
  {
    name: "Jane Smith",
    image: "jane-smith.jpg",
    review: "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
  },
  {
    name: "Alice Johnson",
    image: "alice-johnson.jpg",
    review: "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
  },
  {
    name: "Bob Roberts",
    image: "bob-roberts.jpg",
    review: "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
  },
  {
    name: "Emily Davis",
    image: "emily-davis.jpg",
    review: "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium."
  }
];

function Temoignage()  {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3
  };
  
  return ( <section className='' style={{ backgroundImage: `url(${react1})` }}>
  <div className='w-3/4 m-auto'  >
  <div className='mt-0'>
  <Slider {...settings}>
    {data.map((d => 
  <div className='bg-white h-[450px] text-black rounded-xl' >
    <div className='h-56 rounded-t-xl bg-gray-100 flex justify-center'>
    <img src={d.image} alt='' className='h-44 w-44  rounded-full'/>
    </div>
    <div className='flex flex-col justify-center items-center gap-4 p-4'>
      <p className='text-xl font-semibold'>{d.name}</p>
      <p>{d.review} </p>
    </div>

  </div>
  ))}
</Slider>
  </div>
  </div>
  </section>
  );
}

export default Temoignage;


