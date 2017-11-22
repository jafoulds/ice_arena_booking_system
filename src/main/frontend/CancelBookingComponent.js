import React from 'react';
import requests from './requests';
import {CalendarComponent} from './CalendarComponent';
import Modal from 'react-modal';
const API = 'http://localhost:8080/api';

export class CancelBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
            this.state = {
                showBooking: true
            };
        }

        render() {
           return (
                 <div id="booking">
                        <table><tbody>
                            <tr>
                                <th>Start Time:</th>
                                <td>{this.props.start.toLocaleString()}</td>
                            </tr>
                            <tr>
                                <th>End Time:</th>
                                <td>{this.props.end.toLocaleString()}</td>
                            </tr>
                            <tr>
                                <th>Rink:</th>
                                <td>{this.props.rink}</td>
                            </tr>
                            <tr>
                                <td>
                                    <button type="submit" onClick={(e) => requests.cancelBooking(e, this.props.start, this.props.end, this.props.rink)}>Remove This Booking</button>
                                    <button>Cancel</button>
                                </td>
                            </tr>
                        </tbody></table>
                  </div>
           );
        }


}