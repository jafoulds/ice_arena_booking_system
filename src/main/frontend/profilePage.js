import React from 'react';
import {ProfileSideBar} from './profileSideBar';
import { Route, Link } from 'react-router-dom';
import {CalendarComponent} from './CalendarComponent';
import {Temp} from './temp';
import {CreateGroup} from './createGroup';
import {Schedule} from './schedule';
import './styles/bootstrap.min.css';
import ManageGroups from './manageGroups';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';


export class ProfilePage extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<MuiThemeProvider>
			<div className='row'>
			  <div className='col-2'>
				<ProfileSideBar />
			  </div>
			  <div className='col-9'>
				<Route exact path='/profile/schedule' component={Schedule} />
				<Route exact path='/profile/createGroup' component={CreateGroup} />
				<Route exact path='/profile/manageGroups' component={ManageGroups} />

			</div>
			</div>
			</MuiThemeProvider>


		);
	}


}