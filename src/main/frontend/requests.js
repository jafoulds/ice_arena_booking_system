import {API} from './app';

let getYearAndMonthArgs = (date) => {
	return 'year=' + date.getFullYear() + '&month=' + (date.getMonth()+1);
}

module.exports = {
	getCalendar: (date, callback) => {
		fetch(API + "/getCalendar?" + getYearAndMonthArgs(date), {credentials: 'same-origin'})
            .then(result => {
            	console.log(result);
                return result.json();
            }).then(result => {
            	callback(result)
            }
    	);
    },

    createGroup: (groupName, username, callback) => {
    	fetch(API + "/createGroup?" + 'groupName=' + groupName+ '&username=' + username, 
    		{credentials: 'same-origin'})
            .then(result => {
            	console.log(result);
                return result.json();
            }).then(result => {
            	callback(result)
            }
    	);	
    },

    getCurrentUser: (callback) => {
    	fetch(API + '/getCurrentUser', {credentials: 'same-origin'})
    		.then(result => {
    			// return result.json();
    			return 'ben';
            }).then(result => {
    			callback(result);
    		});
    }
}