import React from 'react';
import requests from './requests';
import './styles/bootstrap.min.css';

export class Login extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			username: '',
			password: ''
		}
	}

	inputChange(e) {
		let newState = {};
		newState[e.target.id] = e.target.value;
		this.setState(newState);
	}

	render() {
		return (
				<form onSubmit={this.createGroup} className='justify-content-center'>
				<div className='row'>
				    <label> Username: 
				    	<input className='m-2' type="text" id='username'
				    		value={this.state.username} 
				    		onChange={(e) => {this.inputChange(e)}}
			    		/> 
			    	</label>
		    	</div>
		    	<div className='row'>
				    <label> Password: 
				    	<input className='m-2' type="password" id='password'
				    		value={this.state.password}
				    		onChange={(e) => {this.inputChange(e)}}
			    		/> 
			    	</label>
		    	</div>
				    <button className='btn btn-primary'>Submit</button>
				</form>
		);
	}


}