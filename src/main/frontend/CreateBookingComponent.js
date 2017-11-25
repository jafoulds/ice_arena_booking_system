import React from 'react';
import requests from './requests';
import {CalendarComponent} from './CalendarComponent';
import Modal from 'react-modal';
import Slider from 'rc-slider';
import Tooltip from 'rc-tooltip';
import 'rc-slider/assets/index.css';

const createSliderWithTooltip = Slider.createSliderWithTooltip;
const Range = createSliderWithTooltip(Slider.Range);
const Handle = Slider.Handle;

const handle = (props) => {
  const { value, dragging, index} = props;
  return (
    <Tooltip
      prefixCls="rc-slider-tooltip"
      overlay={value}
      visible={dragging}
      placement="top"
      key={index}
    >
      <Handle value={value} />
    </Tooltip>
  );
};


export class CreateBookingComponent extends React.Component {

        constructor(props) {
        	super(props);
        	this.timeSlot = Math.round((this.props.end.getTime() - this.props.start.getTime())/(1000*60*60));
            this.state = {
                groups : [],
                selectedGroup: '',
                value: [0, this.timeSlot],
                start: this.props.start,
                end: this.props.end,
                rink: this.props.rink
            };

            this.updateBookingTime = this.updateBookingTime.bind(this);
            this.createBooking = this.createBooking.bind(this);
            this.handleChange = this.handleChange.bind(this);
        }

        componentDidMount() {
            // Get groups owned by current user
            requests.getGroupsByOwnerName (data => {
                this.setState({groups: data});
                console.log("groups: ", this.state.groups);
            });
        }

        // Handle time slider change
        updateBookingTime(value) {
            this.setState({value});
            this.setState({
                start: new Date(this.props.start.getTime() + value[0]*1000*60*60),
                end: new Date(this.props.start.getTime() + value[1]*1000*60*60)
            });
        }

        handleChange(e) {
            this.setState({selectedGroup:e.target.value});
        }

        render() {
            const uri = '/addBooking';
            const dateOptions = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };

            return (
                <div>
                <h4>Booking for Rink #{this.props.rink}</h4>
                <h5>Date: {this.props.start.toLocaleDateString('en-US', dateOptions)}</h5>
                <form onSubmit={this.createBooking}>
                    <div>Select a Group</div>
                    <select value={this.state.selectedGroup} onChange={this.handleChange}>
                        {this.state.groups.map(group => {
                            return <option key={group.id} value={group.groupName}>{group.groupName}</option>})}
                    </select>
                    <div>Select the Length of Booking (Hours):</div>
                    <Range
                        max={this.timeSlot}
                        allowCross={false} dots
                        step={1}
                        defaultValue={this.state.value}
                        tipFormatter={value => '${value}Hours'}
                        onChange={this.updateBookingTime}

                       />
                    <div>From:</div><div>{this.state.start.toLocaleString()}</div>
                    <div>To:</div><div>{this.state.end.toLocaleString()}</div>
                    <button className='btn btn-primary'>Book this Rink</button>
                   </form>
                </div>
            );
        }
        createBooking(e) {
            e.preventDefault();
            requests.addBooking(this.state.start, this.state.end, this.props.rink, this.state.selectedGroup, (resp)=> {
                console.log(resp);
            });
        }

}