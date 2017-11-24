import React from 'react';
import './styles/bootstrap.min.css';
import requests from './requests';
import { Link } from 'react-router-dom';

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
			<div style={{float: 'left'}, {paddingTop: '50px'}}>
				<h1 style={{paddingLeft: '400px'}}>Please Enter The Group Name</h1>
				<form onSubmit={this.createGroup} style={{paddingLeft: '530px'}}>
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
			const {groupName} = this.state;
			alert(`Created new group: ${groupName}`);
			this.setState({groupName: ''});
		});
	}

}