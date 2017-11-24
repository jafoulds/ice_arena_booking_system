import {API} from './app';

let getYearAndMonthArgs = (date) => {
	return 'year=' + date.getFullYear() + '&month=' + (date.getMonth()+1);
}

module.exports = {
	getCalendar: (date, callback) => {
		fetch(API + "/getCalendar?" + getYearAndMonthArgs(date), {credentials: 'same-origin'})
            .then(result => {
                return result.json();
            }).then(result => {
            	callback(result)
            }
    	);
    },

    addOrRemoveBooking: (e, uri, request) =>{
        e.preventDefault();
        fetch(
            API + uri, {
            method: 'post',
            credentials: 'same-origin',
            headers: {
                 'Accept':'application/json',
                 'Content-Type':'application/json',
            },
            body: request,
        })
        .then(function (data) {
          console.log('Request success: ', data);
        })
        .catch(function (error) {
          console.log('Request failure: ', error);
        });

    }
}