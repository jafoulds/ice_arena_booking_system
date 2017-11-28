var React = require('react');
var ReactDOM = require('react-dom');
import {CalendarComponent} from './CalendarComponent'
import {Temp} from './temp'
const API = 'http://localhost:8080/api';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Header } from './headerComponent';
import {ProfilePage} from './profilePage';
import requests from './requests';
import {LoginPage} from './loginPage';
import './styles/bootstrap.min.css';


exports.API = API;
class App extends React.Component {

    constructor(props) {
        super(props);
        console.log(ProfilePage);
    }

    passCalendar() {
        return <CalendarComponent 
            getCalendar={requests.getCalendar} 
            isCreateModal={true}
        />
    }

    render() {
        return (
            <div className='container-fluid'>
                <Header />
                <div className='m-4'>
                <Route exact path='/bookrink' render={this.passCalendar} />
                <Route exact path='/login' component={LoginPage} />
                <Route path='/profile' component={ProfilePage} />
                </div>
            </div>
        );
    }

}

ReactDOM.render(<Router><App /></Router>, document.getElementById('react'))