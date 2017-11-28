import React from 'react';
import './styles/bootstrap.min.css';
import { Link } from 'react-router-dom';
import requests from './requests';
import {HeaderElement} from './headerElement'

export class Header extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			linkStyle: {
				textDecoration: 'none',
				color: '#eee',
			}
		};
		this.logout = this.logout.bind(this);
		this.setHeaders = this.setHeaders.bind(this);
	}

	logout() {
		requests.logout(() => {
			this.setHeaders();
		})
	}

	setHeaders() {
		requests.userIsLoggedIn(data => {
			if (data.authenticated) {
				this.setState({elements: [
					<HeaderElement route='/' text='Home' key='1' />,
		          	<HeaderElement route='/profile' text='Profile' key='2' />,
		          	<HeaderElement route='/bookrink' text='Book Rink' key='3' />,
		          	<HeaderElement route='/login' text='Logout' click={this.logout} key='4' />
		          	
				]});
			} else {
				this.setState({elements: [
					<HeaderElement route='/' text='Home' key='1' />,
		          	<HeaderElement route='/login' text='Login' key='2' />
				]});
			}
		});
	}

	componentWillMount() {
		requests.getCurrentUser(data => {
			console.log('user', data);
		})
		this.setHeaders();
		
	}

	render() {
		return (
			<nav className="navbar bg-secondary text-light row justify-content-end">
				  <div className="col-6">
				  	<HeaderElement route='/' text='Company Name' />
				  </div>
				  <div className="col-6">
				  	<div className='row justify-content-end'>
			          {this.state.elements}
		          	</div>
		          </div>
  		    </nav>
		);
	}
}

