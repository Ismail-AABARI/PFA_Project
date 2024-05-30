/*********************** Decoration de la page Sign in comme Strip.Com******************/
/* import React, { useState,useEffect} from 'react';
import './Signin.css'; // Assurez-vous d'avoir un fichier CSS pour styliser votre composant
import { color } from 'framer-motion';

const Signin = () => {
    // État local pour stocker les valeurs des champs de formulaire
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    // Fonction pour gérer les changements de saisie dans les champs du formulaire
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    // Fonction pour gérer la soumission du formulaire de connexion
    const handleSubmit = (e) => {
        e.preventDefault();
        // Ajoutez ici la logique de traitement de la connexion
    };
    useEffect(() => {
      // Appliquer les styles au body
      document.body.style.display = 'flex';
      document.body.style.alignItems = 'center';
      document.body.style.justifyContent = 'center';
      document.body.style.minHeight = '100vh';
      document.body.style.background = '#f6f9fc';

      // Retirer les styles du body lorsque le composant est démonté
      return () => {
          document.body.style.display = '';
          document.body.style.alignItems = '';
          document.body.style.justifyContent = '';
          document.body.style.minHeight = '';
          document.body.style.background = '';
      };
  }, []);
    return (
        <div className="signin-container">
            <h2>Connectez-vous à votre compte</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit">connexion</button>
            </form>
        </div>
    );
};

export default Signin;
 */
import React , {useEffect} from 'react';
import './Signin.css';
import { Link } from 'react-router-dom';
const Signin = () => {
  useEffect(() => {
    // Appliquer les styles au body
      document.body.classList.add('signin-body-style');
    // Retirer les styles du body lorsque le composant est démonté
    return () => {
      document.body.classList.remove('signin-body-style');
    };
  }, []);
  return (
    <>
      <div class="container-page">
        <h1 className='login-title'>Connectez-vous à votre compte</h1>
        <form>
          <div class="form-group">
            <label for="email">E-mail</label>
            <input type="email" id="email" name="email" placeholder="Entrer your email" required/>
          </div>
          <div class="form-group">
              <div><label for="password">Mot de passe</label></div>
              <input type="password" id="password" name="password" placeholder="Entrer your password" required />
              <span><Link className='password' to='/reset'>Mot de passe oublié ?</Link></span>
          </div>
          <button type="submit" className='bouton'>Connexion</button>
        </form>
        <div class="create-account">
          Vous découvrez OrderVista ? <Link className= 'compte' to='/register'>Créer un compte</Link>
        </div>
      </div>
    </>
  );
};

export default Signin;