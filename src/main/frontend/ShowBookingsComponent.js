import React from 'react';
import requests from './requests';
import Modal from 'react-modal';
const API = 'http://localhost:8080/api';

export class ShowBookingsComponent extends React.Component {

componentDidMount() {
        // Sample api call. Calls findAll then maps users to li tag containing usernames.
        fetch(API + "/findAll", {credentials: 'same-origin'})
            .then(result => {
                return result.json();
            }).then(result => {
                let newUsers = [];
                result.forEach((item) => {
                    newUsers.push(item.username);
                });
                newUsers = newUsers.map((item, index) => {
                    return (<li key={index}>{item}</li>)
                });
                this.setState({users: newUsers});
            }
        );
render() {
        return (
            <div>
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
                    style={{
                        overlay: {
                            zIndex : 1000
                        }
                    }}
                >
                    <CreateBookingComponent
                        start={this.state.start}
                        end={this.state.end}
                        rink={this.state.rink}
                    />
                </Modal>
            </div>

            );
        } else {
            return (<div></div>);
        }


    }
}