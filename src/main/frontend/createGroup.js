import React from 'react';
import './styles/bootstrap.min.css';
import requests from './requests';

export class CreateGroup extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			groupName: ''
		};
		this.groupNameChange = this.groupNameChange.bind(this);
		this.createGroup = this.createGroup.bind(this);
	}

	groupNameChange(e) {
		this.setState({groupName: e.target.value});
	}

	render() {
		return (
			<div>
				<form onSubmit={this.createGroup}>
					<input type="text" value={this.state.groupName} onChange={this.groupNameChange} />
					<button className='btn btn-primary'>Submit</button>
				</form>
			</div>
		);
	}

	createGroup(e) {
		e.preventDefault();
		requests.createGroup(this.state.groupName, (resp)=> {
			console.log(resp);
		});

	}
}