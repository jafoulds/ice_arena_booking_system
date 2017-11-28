import React from 'react';
import './styles/bootstrap.min.css';
import { Link } from 'react-router-dom';

export class ProfileSideBar extends React.Component {


	constructor(props) {
		super(props);
		this.state = {
			linkStyle: {
				textDecoration: 'none',
				color: '#222',
			}
		};
	}

	render() {
		return (
			<div>
			<ul className='list-group' style={{textAlign: 'center'}}>
				<li className='list-group-item'>
					<Link to='/profile/schedule' style={this.state.linkStyle}>Schedule</Link>
				</li>
				<li className='list-group-item'>
					<Link to='/profile/settings' style={this.state.linkStyle}>Settings</Link>
				</li>
				<li className='list-group-item'>
					<Link to='/profile/createGroup' style={this.state.linkStyle}>Create Group</Link>
				</li>
				<li className='list-group-item'>
					<Link to='/profile/manageGroups' style={this.state.linkStyle}>Manage Groups</Link>
				</li>
			</ul>
			</div>
		);
	}


}