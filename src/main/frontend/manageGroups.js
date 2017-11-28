import React from 'react';
import './styles/bootstrap.min.css';
import requests from './requests';
import { Link } from 'react-router-dom';


export class ManageGroups extends React.Component {
	constructor() {
		super();
		this.state = {
			currGroups : [],
			someList: []
		};
		this.getGroups = this.getGroups.bind(this)
	}

	componentDidMount() {
		this.getGroups();
	}

	getGroups(){
		requests.getListOfUserGroups(resp=>{

				console.log(resp);
			this.setState({
				currGroups: resp
			});
		});
	}

	render(){
		return(
			<div>
				{this.state.currGroups.map(group => {
				 return <Group name={group.groupName} />})}
				{this.state.currGroups.map(group => {
				 return <Group name={group.groupMembers} />})}
			</div>



		);
	}


}

function Group (props) {
	return (
		<div>
			{props.name}
		</div>
	)
}