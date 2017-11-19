import React from 'react';
import BigCalendar from 'react-big-calendar';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import moment from 'moment';
import events from './events';
import requests from './requests'

BigCalendar.setLocalizer(
  BigCalendar.momentLocalizer(moment)
);

export class CalendarComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {calendar: []};
    }
    componentDidMount() {
        this.getTimeSlots(new Date());
     }

    getTimeSlots(date) {
        requests.getCalendar(date, (result) => {
            this.setState({calendar: result});
            let timeslots = [];
            this.state.calendar.days.forEach((day) => {
                day.availableTimeSlots.forEach((timeslot) => {
                    timeslots.push(this.calendarDayToDate(day.date, timeslot));
                });
            });
            this.setState({timeslots: timeslots});
        });   
    }

     calendarDayToDate(date, timeslot) {
        let start = new Date(
            date.year, 
            date.monthValue-1, //js dates are zero indexed
            date.dayOfMonth,
            timeslot.startTime.hour,
            timeslot.startTime.minute);
        let end = new Date(
            date.year, 
            date.monthValue-1, 
            date.dayOfMonth,
            timeslot.endTime.hour,
            timeslot.endTime.minute);
        let title = this.getTitleForTimeSlot(timeslot);
        return {start, end, title, allDay: false};
     }

    getClockTimeString(time) {
        return (((time.hour-1) % 12)+1)+":"+ 
            (time.minute < 10 ? '0' + time.minute : time.minute) +
            (time.hour < 12 ? 'am' : 'pm');
    }

     getTitleForTimeSlot(timeslot) {
        let start = this.getClockTimeString(timeslot.startTime);
        let end = this.getClockTimeString(timeslot.endTime);
        return 'rink ' + timeslot.rink.id + '\n' + start + '-' + end;
     }

    render() {
        if (this.state.calendar.month && this.state.timeslots) {
            return (
            <div style={{height:1000 + 'px'}}>
                <h2 style={{textAlign: 'center'}}>
                    {this.state.calendar.month ? this.state.calendar.month.month : ""}
                </h2> 
                <BigCalendar
                    {...this.props}
                    events={this.state.timeslots}
                    views={['month', 'week', 'day']}
                    step={60}
                    defaultDate={new Date()}
                    onNavigate={(date) => {this.getTimeSlots(date)}}
                />
            </div>

            );
        } else {
            return (<div></div>);
        }
        
        
    }
}
