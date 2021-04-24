import React, {useContext, useState, useEffect} from 'react';
import './Login.css'
import fire from './fire';

import {CurrentUserContext} from './CurrentUserContext';


const Login = () => {

    const {user, setUser} = useContext(CurrentUserContext);

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [authError, setAuthError] = useState("")
    const [hasAccount, setHasAccount] = useState(false)
  

    const login = () => {
        fire.auth().signInWithEmailAndPassword(email, password)
          .then(res => {
            console.log("Logging in")
          })
          .catch(err => {
            setAuthError(err.message);
          })
      }
    
      const signup = () => {
        fire.auth().createUserWithEmailAndPassword(email, password)
          .catch(err => {
            setAuthError(err.message);
          });
      }
    
      const logout = () => {
        fire.auth().signOut();
      }
    
      const authListener = () => {
        fire.auth().onAuthStateChanged(user => {
          if (user) {
            setUser(user.email);
            
          } else {
            setUser("");
          }
        });
      };

      useEffect(() => {
        authListener();
      }, []);
    

    return (
        <section className="login">
            <div className="loginContainer">
                <label>email</label>
                <input type="text" autoFocus required value={email} onChange={(e) => setEmail(e.target.value)} />
                <label>password</label>
                <input type="password" autoFocus required value={password} onChange={(e) => setPassword(e.target.value)} />

                <p className="errorMsg">{authError}</p>

                <div className="btnContainer">
                    {
                        hasAccount ? (
                            <><button onClick={login}>Signin</button>
                                <p>No account? <span onClick={() => { setHasAccount(!hasAccount) }}>Signup</span></p>
                            </>
                        ) : (
                            <>
                                <button onClick={signup}>Signup</button>
                                <p>Have an account? <span onClick={() => { setHasAccount(!hasAccount) }}>Signin</span></p>
                            </>
                        )
                    }
                </div>
            </div>
        </section>
    )
}

export default Login