import React from 'react';
import requests from './requests';
import {CalendarComponent} from './CalendarComponent';
import Modal from 'react-modal';
import Slider from 'rc-slider';
import Tooltip from 'rc-tooltip';
import 'rc-slider/assets/index.css';

const Range = Slider.Range;

export class CreateBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
        	this.timeSlot = Math.round((this.props.end.getTime() - this.props.start.getTime())/(1000*60*60));
            this.state = {
                groups : [],
                selectedGroup: null,
                value: [0, this.timeSlot],
                start: this.props.start,
                hours: this.timeSlot,
                end: this.props.end,
                rink: this.props.rink,
                msg: ''
            };

            this.updateBookingTime = this.updateBookingTime.bind(this);
            this.createBooking = this.createBooking.bind(this);
            this.validateForm = this.validateForm.bind(this);
        }

        componentDidMount() {
            // Get groups owned by current user
            requests.getGroupsByOwnerName (data => {
                this.setState({groups: data});
                console.log("groups: ", this.state.groups);
            });
        }

        // Handle time slider change
        updateBookingTime(value) {
            this.setState({value});
            this.setState({
                start: new Date(this.props.start.getTime() + value[0]*1000*60*60),
                end: new Date(this.props.start.getTime() + value[1]*1000*60*60),
                hours: value[1] - value[0]
            });
        }

        validateForm() {
             if (this.state.selectedGroup == null) {
                 this.setState({msg: "A group must be selected before booking."});
                 return false;
             }
             else if (this.state.hours==0) {
                 this.setState({msg: "Booking is for 0 hours!"});
                  return false;
             }
             else {return true};
         }

         getSuccessMessage() {
            if (this.state.bookingCreationSucceeded) {
                return <p className='text-success'>Booking created successfully!</p>
            } else if (this.state.bookingCreationSucceeded === false) {
                return <p className='text-danger'>Booking could not be created</p>    
            }
            return "";
            
         }

        render() {
            const dateOptions = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };

            return (
                <div>
                <h4>Booking for Rink #{this.props.rink}</h4>
                <h5>Date: {this.props.start.toLocaleDateString('en-US', dateOptions)}</h5>
                <form onSubmit={this.createBooking}>
                    <div>For Group:</div>
                    <select
                        value={this.state.selectedGroup || ''}
                        onChange={(e) => this.setState({selectedGroup:e.target.value || null})}>
                        <option key={0} value={''}>Select a group...</option>
                        {this.state.groups.map(group => {
                            return <option key={group.id} value={group.groupName}>{group.groupName}</option>})}
                    </select>
                    <div>Select the Start and End Times of your Booking (Hours):</div>
                    <Range
                        max={this.timeSlot}
                        allowCross={false} dots
                        step={1}
                        defaultValue={this.state.value}
                        onChange={this.updateBookingTime}
                       />
                    <div>From:</div><div>{this.state.start.toLocaleString()}</div>
                    <div>To:</div><div>{this.state.end.toLocaleString()}</div>
                    <div>Total Hours:</div><div>{this.state.hours}</div>
                    <div>{this.state.msg}</div>
                    <button type="submit" className='btn btn-primary'>Book this Rink</button>
                    <div className='btn btn-secondary' onClick={this.props.closeModal}>Exit</div>
                   </form>
                {this.getSuccessMessage()}
                </div>
            );
        }
        createBooking(e) {
            e.preventDefault();
            if (this.validateForm()) {
            let body = {
                'startDate': this.state.start,
                'endDate': this.state.end,
                'rink': this.props.rink,
                'groupName': this.state.selectedGroup
            }
            requests.addBooking(body, (date, resp) => {
                    this.setState({bookingCreationSucceeded: resp.ok})
                    this.props.updateCalendar(date);        
                });
            
            }
        }
}