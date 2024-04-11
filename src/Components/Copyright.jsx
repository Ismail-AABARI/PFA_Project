import React from 'react';

const Copyright = () => {
  const currentYear = new Date().getFullYear();

  return (
    <footer className="bg-black text-white py-4">
      <div className="container-fluid">
        <div className="text-center">
          <p className="mb-0">&copy; {currentYear} Votre Société. Tous droits réservés.</p>
        </div>
      </div>
    </footer>
  );
};

export default Copyright;
