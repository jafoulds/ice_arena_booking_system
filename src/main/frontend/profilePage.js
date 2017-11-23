import React from 'react';
import {ProfileSideBar} from './profileSideBar';
import { Route, Link } from 'react-router-dom';
import {CalendarComponent} from './CalendarComponent';
import {Temp} from './temp';
import {CreateGroup} from './createGroup';
import {Schedule} from './schedule';

export class ProfilePage extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div>
				<ProfileSideBar />
				<Route exact path='/' component={Schedule} />
			</div>
		);
	}


}