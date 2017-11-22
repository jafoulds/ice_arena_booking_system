var React = require('react');
var ReactDOM = require('react-dom');
import {CalendarComponent} from './CalendarComponent'
import {Temp} from './temp'
const API = 'http://localhost:8080/api';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Header } from './headerComponent';
import {ProfilePage} from './profilePage';

exports.API = API;

class App extends React.Component {

    constructor(props) {
        super(props);
        console.log(ProfilePage);
    }

    render() {
        return (
            <div>
                <Header />
                <Route exact path='/calendar' component={CalendarComponent} />
                <Route exact path='/temp' component={Temp} />
                <Route path='/' component={ProfilePage} />
            </div>
        );
    }

}

ReactDOM.render(<Router><App /></Router>, document.getElementById('react'))

