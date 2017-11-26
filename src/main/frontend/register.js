import React from 'react';
import {CalendarComponent} from './CalendarComponent'
import requests from './requests';

export class Register extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<CalendarComponent getCalendar={ requests.getCalendarForUser } />
		);
	}


}