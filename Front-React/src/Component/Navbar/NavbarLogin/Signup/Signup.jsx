import React , {useEffect} from 'react';
import './Signup.css';
import { Link } from 'react-router-dom';
const Signup = () => {
  useEffect(() => {
    // Appliquer les styles au body
      document.body.classList.add('signup-body-style');
    // Retirer les styles du body lorsque le composant est démonté
    return () => {
      document.body.classList.remove('signup-body-style');
    };
  }, []);
  return (
    <>
      <div class="signup-page">
        <h1 className='signup-title'>Créez votre compte OrderVista </h1>
        <form>
          <div class="form-signup">
            <label for="email">E-mail</label>
            <input type="email" id="email" name="email" placeholder="Entrer your email" required/>
          </div>
          <div className="form-country">
            <div><label for="country">Pays</label></div>
            <select name="co" id="country" required>
                <option value="United States">United States</option>
                <option value="Algeria">Algeria</option>
                <option value="Argentina">Argentina</option>
                    <option value="Australia">Australia</option>
                    <option value="Belgium">Belgium</option>
                    <option value="Brazil">Brazil</option>
                    <option value="Canada">Canada</option>
                    <option value="China">China</option>  
                    <option value="Italy">Italy</option>
                    <option value="Japan">Japan</option>
                    <option value="Mexico">Mexico</option>
                    <option value="Morocco">Morocco</option>           
            </select>
          </div>
          <div class="form-signup">
              <div><label for="password">Mot de passe</label></div>
              <input type="password" id="password" name="password" placeholder="Entrer your password" required />
          </div>
          <button type="submit" className='bouton'>Créer un compte</button>
        </form>
        <div class="create-account">
          Vous avez déjà un compte ? <Link to='/login' className='connect'>Connectez-vous</Link>
        </div>
      </div>
      <div className='Contenu'>OrderVista</div>
      <div className='left-side'>
          <div>
            <div className='left-design'>
              <div>
                  <img src="images/success.png" alt="" />
              </div>
              <div>
                <span>Lancez-vous rapidement</span>
              </div>
            </div>
            <div className='contenu'>
                Analysez vos commandes et ventes de produits en détail avec notre plateforme. Suivez les tendances en temps réel et identifiez les meilleurs produits.
            </div>
          </div>
          <div>
            <div className='left-design'>
              <div>
                  <img src="images/success.png" alt="" />
              </div>
              <div>
                <span>Décrypter vos données pour mieux vendre</span>
              </div>
            </div>
            <div className='contenu'>
                Transformez vos données brutes en informations stratégiques avec nos tableaux de bord intuitifs.
            </div>
          </div>
          <div>
            <div className='left-design'>
              <div>
                  <img src="images/success.png" alt="" />
              </div>
              <div>
                <span>Inscrivez-vous dès maintenant</span>
              </div>
            </div>
            <div className='contenu'>
                Lancez-vous en quelques clics et exploitez pleinement le potentiel de votre activité. Votre succès est notre priorité !
            </div>
          </div>
      </div>

    </>
  );
};

export default Signup;


/*
  <div><label for="country">Pays</label></div>
                <select name="co" id="country" required>
                    <option value="United States">United States</option>
                    <option value="Algeria">Algeria</option>
                    <option value="Argentina">Argentina</option>
                    <option value="Australia">Australia</option>
                    <option value="Belgium">Belgium</option>
                    <option value="Brazil">Brazil</option>
                    <option value="Canada">Canada</option>
                    <option value="China">China</option>
                    <option value="Croatia">Croatia (Hrvatska)</option>
                    <option value="Denmark">Denmark</option>
                    <option value="France">France</option>
                    <option value="Germany">Germany</option>
                    <option value="Greece">Greece</option>
                    <option value="Hong Kong">Hong Kong</option>   
                </select>
*/