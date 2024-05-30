import React , {useEffect,useState} from 'react'
import './Login.css'
import user1 from '../../../assets/user.png'
import email from '../../../assets/email.png'
import password from '../../../assets/password.png'
import facebook from '../../../assets/face.png'
import google from '../../../assets/google.png'
import link from '../../../assets/link.png' 
import twitter from '../../../assets/twit.png'
import 'react-toastify/dist/ReactToastify.css';
import { API_URI } from '../../../../config';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
function Login() {

    const [errorMessage, setErrorMessage] = useState('');

/*********************************** Le Signup *******************************************/
    const [user, setUser] = useState({
        name: '',
        email: '',
        password: ''
    });

    const handleChange = e => {
        setUser({ ...user, [e.target.id]: e.target.value });
    };

    const submitSignup = e => {
        e.preventDefault();

        fetch(`${API_URI}/signup`, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        })
        .then(res => res.json())
        .then(res => {
            if (res.error) {
                toast.warning(res.error, 'Please Check form !', {
                    positionClass: "toast-bottom-left",
                });
            } else {
                toast.success('User is created SuccessFully', 'New Accout', {
                    positionClass: "toast-bottom-left",
                });
                props.history.push('/signin');
            }
        })
        .catch(err => toast.error(err, 'Server error !', {
            positionClass: "toast-bottom-left",
        }));
    }
/*********************************** Le Signin *******************************************/

    const [user2, setUser2] = useState({
        email: '',
        password: ''
    })
    const handleChanger = e => {

        setUser2({...user2, [e.target.id]: e.target.value})

    }
    const submitSignin = e => {

        e.preventDefault();

        fetch(`${API_URI}/signin`, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        })
        .then(res => res.json())
        .then(res => {
            if(res.error) {
                toast.warning(res.error, 'Please Check form !', {
                    positionClass: "toast-bottom-left",
                })
            }
            else {
                toast.info('User is authenticated SuccessFully', 'Welcome', {
                    positionClass: "toast-bottom-left",
                })

                localStorage.setItem('jwt_info', JSON.stringify(res))

                props.history.push('/')
            }
            // Votre logique de connexion
            // Si la connexion échoue, définissez le message d'erreur
        setErrorMessage("Nom d'utilisateur ou mot de passe incorrect.");

        })
        .catch(err =>  toast.error(err, 'Server error !', {
                    positionClass: "toast-bottom-left",
                }))
    }
/****************************Partie englobant le signin et le signup**************************** */
    useEffect(() => {
        // Appliquer les styles au body
        document.body.style.display = 'flex';
        document.body.style.alignItems = 'center';
        document.body.style.justifyContent = 'center';
        document.body.style.minHeight = '100vh';
        document.body.style.background = 'linear-gradient(to bottom, #F0F0F0, #E6E6E6)';

        // Retirer les styles du body lorsque le composant est démonté
        return () => {
            document.body.style.display = '';
            document.body.style.alignItems = '';
            document.body.style.justifyContent = '';
            document.body.style.minHeight = '';
            document.body.style.background = '';
        };
    }, []);
    const handleSignIn = () => {
        const container = document.querySelector(".container");
        container.classList.remove("sign-up-mode");
    };
    const handleSignUp = () => {
        const container = document.querySelector(".container");
        container.classList.add("sign-up-mode");
    };
    const handlerSignIn = () => {
        const container = document.querySelector(".container");
        container.classList.remove("sign-up-mode2");
    };
    const handlerSignup = () => {
        const container = document.querySelector(".container");
        container.classList.add("sign-up-mode2");
    };
    const HandleSignIn = (e) => {
        e.preventDefault();
        // Votre logique de connexion
        // Si la connexion échoue, définissez le message d'erreur
        setErrorMessage("Nom d'utilisateur ou mot de passe incorrect.");
    };
    return (
    <>
        <div className="container">
            <div className="signin-signup">
                <form action="" className="sign-in-form" onSubmit={submitSignin}>
                    <h2 className="title">Sign in</h2> 
                    <div className="input-field">
                        <i className='fas fa-user'>
                            <img src={user1} alt="" className='style'/>
                        </i>
                        <input type="text" placeholder='Username' onChange={handleChanger} id='username'/>
                    </div>
                    <div className="input-field">
                        <i className='fas fa-lock'><img src={password} alt="" className='style'/></i>
                        <input type="password" placeholder='Password' onChange={handleChanger} id='password'/>
                    </div>
                    {/* La case à cocher "Souvenir de moi" va ici */}
                    {/* <div className="input-field remember-me">
                        <input type="checkbox" id="remember-me" />
                        <label htmlFor="remember-me">Remember me</label>
                    </div> */}
                    <input type="submit" value="Login" className='btn'/>
                    <p className="social-text">Or Sign in with social platform</p>
                    <div className="social-media">
                        <a href="https://www.facebook.com/" className="social-icon">
                            <i className='fab fa-facebook'><img src={facebook} alt="" className='style'/></i>
                        </a>
                        <a href="https://twitter.com/" className="social-icon">
                            <i className='fab fa-twitter'><img src={twitter} alt="" className='style'/></i>
                        </a>
                        <a href="https://www.google.com/" className="social-icon">
                            <i className='fab fa-google'><img src={google} alt="" className='style'/></i>
                        </a>
                        <a href="https://www.linkedin.com/" className="social-icon">
                            <i className='fab fa-Linkdin-in'> 
                                <link rel="stylesheet" href="" /><img src={link} alt="" className='style'/>
                            </i>
                        </a>
                    </div>
                    <p className="account-text">Don't have an account? <a href="#" id="sign-up-bnt2" onClick={handlerSignup}>Sign up</a></p>
                </form>
                <form action="" className="sign-up-form" onSubmit={submitSignup}>
                    <h2 className="title">Sign up</h2> 
                    <div className="input-field">
                        <i className='fas fa-envelope'><img src={user1} alt="" className='style'/></i>
                        <input type="text" placeholder='Username' onChange={handleChange} id='username'/>
                    </div>
                    <div className="input-field">
                        <i className='fas fa-user'><img src={email} alt="" className='style'/></i>
                        <input type="text" placeholder='Email' onChange={handleChange} id='email'/>
                    </div>
                    <div className="input-field">
                        <i className='fas fa-lock'><img src={password} alt="" className='style'/></i>
                        <input type="password" placeholder='Password' onChange={handleChange} id='password'/>
                    </div>
                    <input type="submit" value="Sign up" className='btn'/>
                    <p className="social-text">Or Sign in with social platform</p>
                    <div className="social-media">
                        <a href="#" className="social-icon">
                            <i className='fab fa-facebook'><img src={facebook} alt="" className='style'/> </i>
                        </a>
                        <a href="" className="social-icon">
                            <i className='fab fa-twitter'><img src={twitter} alt="" className='style'/></i>
                        </a>
                        <a href="" className="social-icon">
                            <i className='fab fa-google'><img src={google} alt="" className='style'/> </i>
                        </a>
                        <a href="" className="social-icon">
                            <i className='fab fa-Linkdin-in'> <img src={link} alt="" className='style'/></i>
                        </a>
                    </div>
                    <p className="account-text">Already have an account? <a href="#" id="sign-in-bnt2" onClick={handlerSignIn}>Sign in</a></p>
                </form>
            </div>              
            <div className="panels-container">
                <div className="panel left-panel">
                    <div className="content">
                        <h3>Welcome Back!</h3>
                        <p>Enter your personal details to use all of site features</p>
                        <button className='btn' id='sign-in-btn' onClick={handleSignIn}>Sign in</button>
                    </div>
                    <img src="progammation.svg" alt=""  className='image'/>
                </div>
                <div className="panel right-panel">
                    <div className="content">
                        <h3>Hello, Friend!</h3>
                        <p>Register with your personal details to use all of sites features</p>
                        <button className='btn' id='sign-up-btn' onClick={handleSignUp}>Sign up</button>
                    </div>
                    <img src="Pic.svg" alt=""  className='image'/>
                </div>
            </div>
        </div>
        {/* Afficher le message d'erreur */}
        {errorMessage && <p className="error-message">{errorMessage}</p>}
    </>
  )
}

export default Login