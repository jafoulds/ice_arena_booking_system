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

    addBooking: (e, start, end, rink) =>{
        e.preventDefault();
        fetch(
            API + '/addBooking', {
            credentials: 'same-origin',
            method: 'post',
            headers: {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        "startDate":start.toJSON(),
                        "endDate":end.toJSON(),
                        "rink":rink,
                        "groupName":"1",
                        "usernameOfBooker":"fake_user"
                })
        })
        .then(function (data) {
          console.log('Request success: ', data);
        })
        .catch(function (error) {
          console.log('Request failure: ', error);
        });

    },

        cancelBooking: (e, id) =>{
            e.preventDefault();
            fetch(
                API + '/cancelBooking', {
                credentials: 'same-origin',
                method: 'post',
                headers: {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        "id":id,
                })
            })
            .then(function (data) {
              console.log('Request success: ', data);
            })
            .catch(function (error) {
              console.log('Request failure: ', error);
            });

        }
}