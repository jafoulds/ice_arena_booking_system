import React from 'react';
import {CalendarComponent} from './CalendarComponent'
import requests from './requests';

export class Register extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div className='col-6'>
			<div className='row justify-content-center'>
					<h1>Sign Up</h1>
				</div>
			<form method='POST' action='/register' className='justify-content-center'>
				<div className='row justify-content-center'>
				    <label> Username: 
				    	<input className='m-2' type="text" name='username'
			    		/> 
			    	</label>
		    	</div>
		    	<div className='row justify-content-center'>
				    <label> Email Address: 
				    	<input className='m-2' type="text" name='emailAddress'
			    		/> 
			    	</label>
		    	</div>
		    	<div className='row justify-content-center'>
				    <label> Password: 
				    	<input className='m-2' type="password" name='password'
			    		/> 
			    	</label>
		    	</div> 
		    	<div className='row justify-content-center'>
				    <button className='btn btn-primary'>Submit</button>
			    </div>
				</form>
			</div>
		);
	}


}