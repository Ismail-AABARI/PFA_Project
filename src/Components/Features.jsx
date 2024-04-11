import React from 'react';
import react1 from "../assets/stars.gif";
import react from "../assets/react.svg";


const features = [
  {
    id: 1,
    title: "Sécurité",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    icon: react
  },
  {
    id: 2,
    title: "Simplicité",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    icon: react
  },
  {
    id: 3,
    title: "Efficacité",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    icon: react
  },
  {
    id: 4,
    title: "Fiabilité",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    icon: react
  },
  {
    id: 5,
    title: "Flexibilité",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    icon: react
  },
  {
    id: 6,
    title: "Innovation",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    icon: react
  }
];


const Features = () => {
  return (
    <section className="py-10 bg-black" style={{ backgroundImage: `url(${react1})` }}>
      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        <h2 className="text-3xl font-extrabold text-white text-center mb-12">Nos fonctionnalités</h2>
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <div key={feature.id} className="relative overflow-hidden">
              <div className={`relative bg-gradient-to-br from-blue-200 to-blue-100 rounded-lg p-6 flex flex-col items-center shadow-xl transform hover:scale-105 transition duration-300 border-r-blue-700 border-l-blue-700 border-2 border-blue-700 animate-${index % 2 === 0 ? 'left' : 'right'}`}>
                <img src={feature.icon} alt={feature.title} className="w-12 h-12 text-blue-400 mb-4" />
                <h3 className="text-lg font-semibold text-gradient mb-2">{feature.title}</h3>
                <p className="text-sm text-black text-center">{feature.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Features;
