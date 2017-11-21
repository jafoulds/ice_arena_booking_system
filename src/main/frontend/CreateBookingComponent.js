import React from 'react';



export class CreateBookingComponent extends React.Component {
    
    constructor(props) {
    	super(props)
    }

    render() {
    	console.log(this.props);
        return (
        	<div>
        		<h1>{this.props.start.getHours()}</h1>
        		<h1>{this.props.end.toDateString()}</h1>
        		<h1>{this.props.rink}</h1>
        	</div>
    	);
    }
}