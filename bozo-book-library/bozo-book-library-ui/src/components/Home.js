import React, { useState, useContext} from 'react';
import {
    Route, 
    NavLink,
    HashRouter
  } from "react-router-dom";

  
import Login from './Login';
import BookList from './BookList';
import Library from './Library';
import {CurrentUserContext} from './CurrentUserContext';

const Home = () => {
    const { user } = useContext(CurrentUserContext);

    return(
        <div className="App">
        {user ? (
          <div>
            <HashRouter>
              <ul className="header">
                <li><NavLink to="/">Browse</NavLink></li>
                <li><NavLink to="/library">My Library</NavLink></li>
              </ul>
              <div className="content">
                <Route exact path="/" component={BookList} />
                <Route path="/library" component={Library} />
              </div>
            </HashRouter>
          </div>
        ) : (
          <Login/>
        )}
      </div>

    )
};

export default Home;