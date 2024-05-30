import React , {useEffect} from 'react'
import { Link } from 'react-router-dom';
import './Reset.css'
function Reset() {
    useEffect(() => {
        // Appliquer les styles au body
          document.body.classList.add('reset-body-style');
        // Retirer les styles du body lorsque le composant est démonté
        return () => {
          document.body.classList.remove('reset-body-style');
        };
      }, []);
  return (
    <>
    <div class="reset-page">
      <h1 className='reset-title'>Réinitialisation du mot de passe</h1>
      <div className='designer'>
            <span>Saisissez l'adresse e-mail associée à votre compte et nous vous 
                enverrons un lien pour réinitialiser votre mot de passe.</span>
      </div>
      <form>
        <div class="form-reset">
          <label for="email">E-mail</label>
          <input type="email" id="email" name="email" placeholder="Entrer your email" required/>
        </div>
        <button type="submit" className='buttom'>Continuer</button>
      </form>
      <div class="retour">
        <Link to='/login' className='linkage'>Revenir à la page de connexion</Link>
      </div>
    </div>
    <div className='bas-page'>
     Vous n'avez pas de compte ? <Link className='connect' to={'/register'}>S'incrire</Link>
    </div>
    <div className='Content'>
        <div>
          <img src="images/lock.png" alt="locke/png" />
        </div>
        <div>
          <span>Conseil en matière de sécurité</span>
        </div>
    </div>
    <div className='paragraphe'>
        Installez uniquement les extensions de navigateur provenant d'entreprises en qui vous avez confiance. Certaines extensions de navigateur malveillantes peuvent consulter vos mots de passe et compromettre votre sécurité.
    </div>
  </>
  );
};

export default Reset