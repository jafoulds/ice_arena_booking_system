import React from 'react';
import requests from './requests';
import {CalendarComponent} from './CalendarComponent';
import Modal from 'react-modal';
import dateUtils from './dateUtils';

export class CancelBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
            this.state = {
                group : '',
                start: new Date(),
                end: new Date(),
                rink: this.props.rink,
                msg: ''
            };
            this.cancelBooking = this.cancelBooking.bind(this);
        }

        componentDidMount() {
            // Get booking to delete
            requests.getBooking(this.props.id, data => {
                this.setState({
                    group: data.groupName,
                    rink: data.rink.id,
                    start: dateUtils.convertJsonToJSDate(data.startDate),
                    end: dateUtils.convertJsonToJSDate(data.endDate)
                });
            });
        }

        getSuccessMessage() {
            if (this.state.bookingCreationSucceeded) {
                return <p className='text-success'>Booking cancelled successfully!</p>
            } else if (this.state.bookingCreationSucceeded === false) {
                return <p className='text-danger'>Booking was unable to be cancelled</p>    
            }
            return "";
            
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
                        <button type="submit" className='btn btn-primary'>Cancel Booking</button>
                        <div className='btn btn-secondary' onClick={this.props.closeModal}>Exit</div>
                     </form>
                     {this.getSuccessMessage()}
                </div>
            );
        }
        cancelBooking(e) {
            e.preventDefault();
            requests.cancelBooking(this.props.id, (resp)=> {
                console.log(resp);
                this.setState({bookingCreationSucceeded: resp.ok})
                console.log(this.state);
                this.props.updateCalendar(this.state.start);
            });
        }

}