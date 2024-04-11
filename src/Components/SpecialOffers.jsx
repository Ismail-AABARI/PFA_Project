import React from 'react';

const BuyNowButton = () => {
  return (
    <button className="mt-4 bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">Acheter maintenant</button>
  );
};

const SpecialOffers = () => {
  return (
    <section className="bg-black py-8">
      <div className="container mx-auto">
        <h2 className="text-2xl text-white font-semibold mb-4">Offres spéciales et promotions</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mx-0"> {/* Ajout de la classe mx-0 */}
          {/* Card 1 */}
          <div className="bg-white p-4 rounded-lg shadow-md">
            <h3 className="text-lg font-semibold mb-2">Offre spéciale 1</h3>
            <p>Description de l'offre spéciale. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            <BuyNowButton />
          </div>

          {/* Card 2 */}
          <div className="bg-white p-4 rounded-lg shadow-md">
            <h3 className="text-lg font-semibold mb-2">Offre spéciale 2</h3>
            <p>Description de l'offre spéciale. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            <BuyNowButton />
          </div>

          {/* Card 3 */}
          <div className="bg-white p-4 rounded-lg shadow-md">
            <h3 className="text-lg font-semibold mb-2">Offre spéciale 3</h3>
            <p>Description de l'offre spéciale. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            <BuyNowButton />
          </div>
        </div>
      </div>
    </section>
  );
};

export default SpecialOffers;
