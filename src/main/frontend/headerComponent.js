import React from 'react';
import './styles/bootstrap.min.css';
import { Link } from 'react-router-dom';

export class Header extends React.Component {

	constructor(props) {
		super(props);
		console.log(this);
		this.state = {
			linkStyle: {
				textDecoration: 'none',
				color: '#eee',
			}
		};
	}

	render() {
		return (
			<nav className="navbar bg-secondary text-light row justify-content-end">
				  <div className="col-6">
				  	<div className="navbar-brand col-2">
			          	<Link to="/" style={this.state.linkStyle}>
			          		Company Name
		          		</Link>
		          	</div>
				  </div>
				  <div className="col-6">
			          <div className="navbar-brand col-2">
			          	<Link to="/" style={this.state.linkStyle}>
			          		Home
		          		</Link>
			          </div>
			          <div className="navbar-brand col-2">
			          	<Link to="/login" style={this.state.linkStyle}>
			          		Login
		          		</Link>
			          </div>
	  		          <div className="navbar-brand col-2">
			          	<Link to="/profile" style={this.state.linkStyle}>
			          		Profile
		          		</Link>
			          </div>
			          <div className="navbar-brand col-2">
			          	<Link to="/group" style={this.state.linkStyle}>
			          		Groups
		          		</Link>
			          </div>
			          <div className="navbar-brand col-2">
			          	<Link to="/bookrink" style={this.state.linkStyle}>
			          		Bookings
		          		</Link>
			          </div>
		          </div>
  		    </nav>
		);
	}
}

