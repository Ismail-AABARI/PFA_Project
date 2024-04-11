import React from 'react';


 // Données pour les différentes sections du pied de page
 const sections = [
  {
    title: "Description de la société",
    content: "Insérer une brève description de votre entreprise ici.",
  },
  {
    title: "Liens utiles",
    links: [
      { name: "Accueil", url: "#" },
      { name: "Produits", url: "#" },
      { name: "Services", url: "#" },
      { name: "Contact", url: "#" },
    ],
  },
  {
    title: "Réseaux sociaux",
    links: [
      { name: "Facebook", url: "#" },
      { name: "Twitter", url: "#" },
      { name: "Instagram", url: "#" },
      { name: "LinkedIn", url: "#" },
    ],
  },
];


const Footer = () => {
  return (
    <footer className="bg-black text-white py-8 ml-40 mt-10" >
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        {/* Mapper les sections du pied de page */}
        {sections.map((section, index) => (
          <div key={index}>
            <h3 className="text-lg font-semibold mb-4">{section.title}</h3>
            {/* Vérifier si la section contient des liens */}
            {section.links ? (
              <ul>
                {/* Mapper les liens de la section */}
                {section.links.map((link, linkIndex) => (
                  <li key={linkIndex}>
                    <a href={link.url} className="text-white hover:text-gradient">{link.name}</a>
                  </li>
                ))}
              </ul>
            ) : (
              <p>{section.content}</p>
            )}
          </div>
        ))}
      </div>
    </footer>
  );
};

export default Footer;
