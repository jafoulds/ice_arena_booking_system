var React = require('react');
var ReactDOM = require('react-dom');
import {CalendarComponent} from './CalendarComponent'
const API = 'http://localhost:8080/api';

exports.API = API;

class TestComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
    }

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

        // Sample api call. Calls showBookings then maps bookings to li tag containing usernames.
        fetch(API + "/showBookings", {credentials: 'same-origin'})
            .then(result => {
                return result.json();
            }).then(result => {
                let newBookings = [];
                result.forEach((item) => {
                    newBookings.push(item.id);
                });
                newBookings = newBookings.map((item, index) => {
                    return (<li key={index}>{item}</li>)
                });
                this.setState({bookings: newBookings});
            }
        );

    }

    render() {
        return (
            <div>
            <p>hello</p>
            </div>
        );
    }

}

ReactDOM.render(<CalendarComponent />, document.getElementById('react'))

