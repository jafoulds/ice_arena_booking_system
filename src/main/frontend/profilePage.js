import React from 'react';
import {ProfileSideBar} from './profileSideBar';
import { Route, Link } from 'react-router-dom';
import {CalendarComponent} from './CalendarComponent';
import {Temp} from './temp';
import {CreateGroup} from './createGroup';
import {Schedule} from './schedule';
import {ManageGroups} from './manageGroups';

export class ProfilePage extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div>
			<div>
				<ProfileSideBar />
			</div>
			<div>
				<Route exact path='/profile/createGroup' component={CreateGroup} />
				<Route exact path='/profile/manageGroups' component={ManageGroups} />
			</div>
			</div>

		);
	}


}