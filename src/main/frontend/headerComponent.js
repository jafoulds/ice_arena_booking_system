import React from 'react';
import './styles/bootstrap.min.css';
import { Link } from 'react-router-dom';

export class Header extends React.Component {

	constructor(props) {
		super(props);
		console.log(this);
		this.state = {
			color: {
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
			          	<Link to="/" style={this.state.color}>
			          		Company Name
		          		</Link>
		          	</div>
				  </div>
				  <div className="col-6">
			          <div className="navbar-brand col-2">
			          	<Link to="/" style={this.state.color}>
			          		Home
		          		</Link>
			          </div>
			          <div className="navbar-brand col-2">
			          	<Link to="profile" style={this.state.color}>
			          		Login
		          		</Link>
			          </div>
	  		          <div className="navbar-brand col-2">
			          	<Link to="profile" style={this.state.color}>
			          		Profile
		          		</Link>
			          </div>
			          <div className="navbar-brand col-2">
			          	<Link to="/group" style={this.state.color}>
			          		Groups
		          		</Link>
			          </div>
			          <div className="navbar-brand col-2">
			          	<Link to="/calendar" style={this.state.color}>
			          		Bookings
		          		</Link>
			          </div>
		          </div>
  		    </nav>
		);
	}
}

