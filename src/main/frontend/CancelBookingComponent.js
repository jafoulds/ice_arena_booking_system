import React from 'react';
import requests from './requests';
import {CalendarComponent} from './CalendarComponent';
import Modal from 'react-modal';

export class CancelBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
        }



        componentDidMount() {
            requests.getGroupsByOwnerName (data => {
                this.setState({groups: data});
                console.log("groups: ", this.state.groups);
            });
        }


        render() {
            const uri = '/cancelBooking';
            const Range = Slider.Range;
            const timeSlot = this.state.end.getTime() - this.state.start.getTime();
            return (
                <div>
                <h4>Booking for Rink: {this.props.rink}</h4>
                <div>Date:</div><div>{this.state.start.toLocaleDateString()}</div>
                <form>
                    <h3>Length of Booking (Hours)</h3>
                    <div>From:</div><div>{this.state.start.toLocaleString()}</div>
                    <div>To:</div><div>{this.end.toLocaleString()}</div>
                    <div>Select a Group</div>
                    <select>
                        {this.state.groups.map(group => {
                            return <option value={group.groupName}>{group.groupName}</option>})}
                    </select>
                    <button onClick={} />
                   </form>
                </div>
            );
        }

}
