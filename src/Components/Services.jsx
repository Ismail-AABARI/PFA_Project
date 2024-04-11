import React from 'react';
import futuristicImage from "../assets/react.svg";
import react1 from "../assets/stars.gif";

const FuturisticSection = () => {
  return (
    <section className="py-12 bg-gray-900" style={{ backgroundImage: `url(${react1})` }}>
      <div className="container mx-auto flex flex-col lg:flex-row items-center justify-between">
        {/* Image */}
        <div className="w-full lg:w-1/2 mb-8 lg:mb-0">
          <img src={futuristicImage} alt="Futuristic" className="rounded-lg shadow-xl w-full" />
        </div>
        {/* Texte */}
        <div className="w-full lg:w-1/2 text-white">
          <h2 className="text-4xl lg:text-5xl font-bold mb-6 text-gradient">Futurisme Moderne</h2>
          <p className="text-lg mb-6 leading-relaxed tracking-wide">Découvrez notre approche révolutionnaire dans la création de solutions pour le futur. Nous combinons la technologie de pointe avec un design innovant pour offrir des expériences uniques à nos clients.</p>
          <p className="text-lg leading-relaxed tracking-wide">Notre équipe de visionnaires travaille sans relâche pour façonner un avenir où la technologie et l'humanité coexistent harmonieusement.</p>
          <div className="mt-8 text-sm uppercase tracking-wider font-semibold border-t-2 border-white pt-4">
            Explorez notre vision
          </div>
        </div>
      </div>
    </section>
  );
};

export default FuturisticSection;
