import React from 'react';
import BigCalendar from 'react-big-calendar';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import moment from 'moment';
import requests from './requests';
import {CreateBookingComponent} from './CreateBookingComponent';
import {CancelBookingComponent} from './CancelBookingComponent';
import Modal from 'react-modal';
import dateUtils from './dateUtils';

BigCalendar.setLocalizer(
  BigCalendar.momentLocalizer(moment)
);

export class CalendarComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            calendar: [],
            showModal:false
        };
        this.closeModal = this.closeModal.bind(this);
        this.getTimeSlots = this.getTimeSlots.bind(this);
    }
    componentDidMount() {
        this.getTimeSlots(new Date());
    }

    getTimeSlots(date) {
        this.props.getCalendar(date, (result) => {
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
        let bookingId = timeslot.bookingId;
        return {start, end, title, allDay: false, rink: timeslot.rink.id, bookingId};
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

    openModal(event) {
        if (this.props.isCreateModal) {
            this.setState({
                start: event.start, 
                end: event.end, 
                rink: event.rink,
                isCreateBookingModalOpen: true
            });    
        } else {
            this.setState({
                bookingId: event.bookingId,
                isCreateBookingModalOpen: true
            });
        }
    }

    closeModal() {
        this.setState({ isCreateBookingModalOpen: false });
    }

    getModalComponent() {
        if (this.props.isCreateModal) {
            return (
                <CreateBookingComponent
                    start={this.state.start}
                    end={this.state.end}
                    rink={this.state.rink}
                    closeModal={this.closeModal}
                    updateCalendar={this.getTimeSlots}
                />
            );
        }
        return (
            <CancelBookingComponent
                id={this.state.bookingId}
                closeModal={this.closeModal}
                updateCalendar={this.getTimeSlots}
            />
        );
    }

    render() {
        if (this.state.calendar.month && this.state.timeslots) {
            return (
            <div style={{height:1000 + 'px'}}>
                <h2 style={{textAlign: 'center'}}>
                    {this.state.calendar.month ? this.state.calendar.month.month : ""}
                </h2> 
                <BigCalendar
                    selectable
                    onSelectEvent={event=> {this.openModal(event)}}
                    events={this.state.timeslots}
                    views={['month', 'week', 'day']}
                    step={60}
                    defaultDate={new Date()}
                    onNavigate={(date) => {this.getTimeSlots(date)}}
                />
                <Modal
                    isOpen={this.state.isCreateBookingModalOpen}
                    contentLabel="Modal"
                    onRequestClose={this.closeModal}
                    style={{
                        overlay: {
                            zIndex : 1000,
                        }
                    }}
                >
                    { this.getModalComponent() }
                </Modal>
            </div>

            );
        } else {
            return (<div></div>);
        }     
    }
}
