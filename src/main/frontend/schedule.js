import React from 'react';
import {CalendarComponent} from './CalendarComponent'
import requests from './requests';

export class Schedule extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<CalendarComponent 
				getCalendar={ requests.getCalendarForUser } 
				isCreateModal={ false }
			/>
		);
	}


}