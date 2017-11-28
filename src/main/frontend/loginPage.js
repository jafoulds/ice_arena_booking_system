import React from 'react';
import requests from './requests';
import './styles/bootstrap.min.css';
import {Login} from './login';
import {Register} from './register';

export class LoginPage extends React.Component {
	constructor(props) {
		super(props);
		console.log('register', Register);
	}

	render() {
		return (
			<div className='row'>
				<Login />
				<Register />
			</div>
		);
	}


}