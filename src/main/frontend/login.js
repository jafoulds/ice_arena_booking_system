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

	render() {
		return (
			<div className='col-6'>
				<div className='row justify-content-center'>
					<h1>Login</h1>
				</div>
				<form method='POST' action='/login' className='justify-content-center'>
				<div className='row justify-content-center'>
				    <label> Username: 
				    	<input className='m-2' type="text" name='username'
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