import React from 'react';
import './styles/bootstrap.min.css';
import { Link } from 'react-router-dom';

export class ProfileSideBar extends React.Component {


	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div className='col-2' style={{float: 'left'}}>
			<ul className='list-group' style={{textAlign: 'center'}}>
				<li className='list-group-item'>
					<Link to='/profile/schedule'>Schedule</Link>
				</li>
				<li className='list-group-item'>
					<Link to='/profile/settings'>Settings</Link>
				</li>
				<li className='list-group-item'>
					<Link to='/profile/createGroup'>Create Group</Link>
				</li>
				<li className='list-group-item'>
					<Link to='/profile/manageGroups'>Manage Groups</Link>
				</li>
			</ul>
			</div>
		);
	}


}