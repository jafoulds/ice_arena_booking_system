var React = require('react');
var ReactDOM = require('react-dom');
import {CalendarComponent} from './CalendarComponent'
import {Temp} from './temp'
const API = 'http://localhost:8080/api';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Header } from './headerComponent';
import {ProfilePage} from './profilePage';
import requests from './requests';


exports.API = API;
class App extends React.Component {

    constructor(props) {
        super(props);
        console.log(ProfilePage);
    }

    passCalendar() {
        return <CalendarComponent getCalendar={requests.getCalendar} />
    }

    render() {
        return (
            <div>
                <Header />
                <Route exact path='/bookrink' render={this.passCalendar} />
                <Route exact path='/temp' component={Temp} />
                <Route path='/profile' component={ProfilePage} />
            </div>
        );
    }

}

ReactDOM.render(<Router><App /></Router>, document.getElementById('react'))

