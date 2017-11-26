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

    getCalendarForUser: (date, callback) => {
        fetch(API + "/getCalendarForUser?" + getYearAndMonthArgs(date), {credentials: 'same-origin'})
            .then(result => {
                return result.json();
            }).then(result => {
                console.log(result);
                callback(result)
            }
        );
    },

    createGroup: (groupName, callback) => {
    	fetch(API + "/createGroup?" + 'groupName=' + groupName,
    		{credentials: 'same-origin'})
            .then(result => {
            	callback(result)
            }
    	);	
    },

    getCurrentUser: (callback) => {
    	fetch(API + '/getCurrentUser', {credentials: 'same-origin'})
    		.then(result => {
    			return result.json();
            }).then(result => {
    			callback(result);
    		});
    },

     getGroupsByOwnerName: (callback) => {
        fetch(API + "/getGroupsByOwnerName", {credentials: 'same-origin'})
          .then(result => {
                return result.json();
            })
          .then(result => {
               callback(result);
          })
          .catch(function (error) {
            console.log('Request failure: ', error);
          });
    },

    getBooking: (id, callback) => {
        fetch(API + "/getBooking?id=" + id, {credentials: 'same-origin'})
             .then(result => {
                 return result.json();
             }).then(result => {
                callback(result)
             });
    },

    addBooking: (start, end, rink, group, callback) => {
        fetch(
            API + '/addBooking', {
            method: 'post',
            credentials: 'same-origin',
            headers: {
                 'Accept':'application/json',
                 'Content-Type':'application/json',
            },
            body: JSON.stringify({
                'startDate': start,
                'endDate': end,
                'rink': rink,
                'groupName': group
            })
        })
        .catch(function (error) {
            console.log('Request failure: ', error);
          })
        .then(result => {
           callback(result)
         });
      },

      cancelBooking: (id, callback) => {
           fetch(API + "/cancelBooking?id=" + id, {credentials: 'same-origin'})
              .then(result => {
                    return result.json();
                 })
                 .catch(function (error) {
                    console.log('Request failure: ', error);
                  })
                .then(result => {
                    callback(result)
                });
      }
}