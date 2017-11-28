import React from 'react';
import './styles/bootstrap.min.css';
import requests from './requests';
import { Link } from 'react-router-dom';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';



export default class ManageGroups extends React.Component {
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

			 <Table >     		
     			<TableHeader>
     				<TableRow>
     					<TableHeaderColumn>GroupName</TableHeaderColumn>
           				<TableHeaderColumn>Owner</TableHeaderColumn>
            			<TableHeaderColumn>Members</TableHeaderColumn>
          			</TableRow>
        		</TableHeader>

        		<TableBody>
          			{this.state.currGroups.map((group, index) => {
           				return <TableRow key={`${index}-${group.groupName}`}>
			              <TableRowColumn>{group.groupName}</TableRowColumn>
			              <TableRowColumn>{group.ownerName}</TableRowColumn>
			              <TableRowColumn>{group.groupMembers.map(member => member.username)}</TableRowColumn>
			            </TableRow>  
		          })}
		        </TableBody>
		    </Table>
		    </div>

		);
	}



}
//export default ManageGroups;

