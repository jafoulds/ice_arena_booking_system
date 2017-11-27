import React from 'react';
import requests from './requests';
import {CalendarComponent} from './CalendarComponent';
import Modal from 'react-modal';

export class CancelBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
            this.state = {
                group : '',
                start: new Date(),
                end: new Date(),
                rink: '',
                msg: ''
            };
            this.cancelBooking = this.cancelBooking.bind(this);
        }

        componentDidMount() {
            // Get booking to delete
            requests.getBooking (this.props.id, data => {
                this.setState({
                    group: data.groupName,
                    rink: data.rink.id,
                    start: new Date(
                               data.startDate.year,
                               data.startDate.monthValue-1, //js dates are zero indexed
                               data.startDate.dayOfMonth,
                               data.startDate.hour,
                               data.startDate.minute),
                    end: new Date(
                               data.endDate.year,
                               data.endDate.monthValue-1, //js dates are zero indexed
                               data.endDate.dayOfMonth,
                               data.endDate.hour,
                               data.endDate.minute),
                    });
                console.log("booking: ", this.state);
            });
        }

        render() {
            const dateOptions = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };

            return (
                <div>
                    <h4>Cancel Booking for Rink #{this.state.rink}</h4>
                    <h5>Date: {this.state.start.toLocaleDateString('en-US', dateOptions)}</h5>
                    <form onSubmit={this.cancelBooking}>
                        <div>For Group:</div>
                        <div>{this.state.group}</div>
                        <div>From:</div><div>{this.state.start.toLocaleString()}</div>
                        <div>To:</div><div>{this.state.end.toLocaleString()}</div>
                        <div>Total Hours:</div><div>
                            {Math.round((this.state.end.getTime() - this.state.start.getTime())/(1000*60*60))} Hours
                        </div>
                        <div>{this.state.msg}</div>
                        <button type="submit" className='btn btn-primary'>Delete this Booking</button>
                        <div className='btn btn-warning' onClick={this.props.closeModal}>Cancel</div>
                     </form>
                </div>
            );
        }
        cancelBooking(e) {
            e.preventDefault();
            console.log(this.state);
            requests.cancelBooking(this.props.id, (resp)=> {
                console.log(resp);
            });
            this.props.closeModal();
        }

}