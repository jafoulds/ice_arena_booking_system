import {API} from './app';
import dateUtils from './dateUtils';

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
                console.log('json', result);
                return result.json();
            }).then(result => {
                console.log('calendar results', result);
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
          console.log(result);
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

    addBooking: (body, callback) => {
        dateUtils.toLocalTime(body.startDate);
        dateUtils.toLocalTime(body.endDate);
        fetch(
            API + '/addBooking', {
            method: 'post',
            credentials: 'same-origin',
            headers: {
                 'Accept':'application/json',
                 'Content-Type':'application/json',
            },
            body: JSON.stringify( body )
        })
        .catch(function (error) {
            console.log('Request failure: ', error);
          })
        .then(result => {
           callback(body.startDate, result)
         });
      },

      cancelBooking: (id, callback) => {
          fetch(API + "/cancelBooking?id=" + id, {credentials: 'same-origin'})
               .catch(function (error) {
                  console.log('Request failure: ', error);
                })
              .then(result => {
                  callback(result)
              });
      },

      userIsLoggedIn: (callback) => {
          fetch(API + "/userIsLoggedIn", {credentials: 'same-origin'})
              .then(result => {
                  return result.json();
              }).then(result => {
                  callback(result)
              }); 
      },

      logout: (callback) => {
        fetch('/logout', {credentials: 'same-origin', method: 'post'})
          .then(result => {
            callback(result)
          })
      },

    getListOfUserGroups: (callback) => {
        fetch(API + '/getListOfUserGroups', {credentials: 'same-origin'})
            .then(result => {
                return result.json();
            }).then(result => {
                callback(result);
            });
    },

    findAll: (callback) => {
        fetch(API + '/findAll')
            .then(result => {
                return result.json();
            }).then(result => {
                callback(result);
            });
    }

}