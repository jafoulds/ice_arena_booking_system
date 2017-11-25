import React from 'react';
import requests from './requests';
import Modal from 'react-modal';
import 'react-datepicker/dist/react-datepicker.css';
import {CalendarComponent} from './CalendarComponent';


export class CreateBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
            this.state = {groups : []};
        }

        componentWillMount() {
            requests.getGroupsByOwnerName (data => {
                this.setState({groups: data});
                console.log("groups: ", this.state.groups);
            });
        }

        render() {
            const uri = '/addBooking';
            return (
                <div>
                <form>
                    <BookingInfo start={this.props.start} end={this.props.end} rink={this.props.rink} />
                    <div>Select a Group</div>
                    <select>
                        {
                        this.state.groups.map(group => {
                            return <option value={group.groupName}>{group.groupName}</option>})
                        }
                    </select>
                   </form>
                </div>
            );
        }
}

function BookingInfo(props) {
    return (<div>
                <DatePicker selected={props.start.toLocaleString()} onChange={this.handleChange} />;
        <div>Start Time:</div><div></div>
        <div>End Time:</div><div>{props.end.toLocaleString()}</div>
        <div>Rink:</div><div>{props.rink}</div>
        <div>Group:</div><div>{props.rink}</div>
    </div>);
}