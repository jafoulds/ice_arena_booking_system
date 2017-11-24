import React from 'react';
import requests from './requests';
import Modal from 'react-modal';
import {CalendarComponent} from './CalendarComponent';


export class CreateBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
            this.state = {
                // isCreate: this.props.isCreate;
                isCreate : true
            };
        }

        render() {
            const isCreate = this.state.isCreate;
            const uri = isCreate ? '/addBooking' : '/cancelBooking';
            const request = isCreate ?
                JSON.stringify({
                'startDate':this.props.start.toJSON(),
                'endDate':this.props.end.toJSON(),
                'rink':this.props.rink,
                'groupName':"1",
                'usernameOfBooker':"fake_user"})
                :
                JSON.stringify({"id":id});
            return (
                <div>
                    <BookingInfo start={this.props.start} end={this.props.end} rink={this.props.rink} />
                    <button onClick={(e) => requests.addOrRemoveBooking(e, uri, request)}>
                        {isCreate ? 'Book this Rink' : 'Cancel this Booking'}
                    </button>
                </div>
            );
        }
}

function BookingInfo(props) {
    return (<div>
        <div>Start Time:</div><div>{props.start.toLocaleString()}</div>
        <div>End Time:</div><div>{props.end.toLocaleString()}</div>
        <div>Rink:</div><div>{props.rink}</div>
    </div>);
}