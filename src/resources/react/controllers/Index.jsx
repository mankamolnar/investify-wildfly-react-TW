import React from 'react';
import {Link} from 'react-router';
import {NavBar} from '../components/NavBar';
import {MoneyPool} from '../components/MoneyPool';
import AuthService from '../services/AuthService';

// *** PAGES ***
export class Index extends React.Component {
    constructor() {
        super();
        this.state = {loggedIn:false};
        this.login = this.login.bind(this)
    }

    login() {
        let user = document.getElementById("user");
        let password = document.getElementById("password");

        this.authService = new AuthService();
        this.authService.authenticate(user, password).then(r => this.setState(r));
    }

    render() {
        return (
            <div>
                <NavBar loggedIn={this.state.loggedIn} login={this.login} />
                <MoneyPool loggedIn={this.state.loggedIn} login={this.login} />
            </div>
        );
    }
}