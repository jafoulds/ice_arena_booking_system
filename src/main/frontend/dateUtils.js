


module.exports = { 
	convertJsonToJSDate: (date) => {
		return new Date(
		   date.year,
		   date.monthValue-1, //js dates are zero indexed
		   date.dayOfMonth,
		   date.hour,
		   date.minute);
	},

	toLocalTime: (date) => {
		let minutes = date.getMinutes() - date.getTimezoneOffset();
		date.setMinutes(minutes); 
	}
}
