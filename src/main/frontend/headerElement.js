import React from 'react';
import './styles/bootstrap.min.css';
import { Link } from 'react-router-dom';

export class HeaderElement extends React.Component {

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
          	<div className="navbar-brand col-2">
          		<Link to={this.props.route} style={this.state.linkStyle} onClick={this.props.click}>
          			{this.props.text}
	      		</Link>
	        </div>
		);
	}
}

