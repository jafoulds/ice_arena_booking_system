### Project Overview

* Developing a web application that supports scheduled bookings and maintenance scheduling for an ice rink complex housing multiple rinks;
* Provides service to community groups represented by a system user who can book rink time for select rinks;
* Allows arena managers to review bookings, and notify group members of booking changes or other updates;
* Spring MVC Runtime:
** Use of Spring Framework allows for clear separate of the model, view, and controllers. 
** Registration, login, rink booking, and calendar requests are handled by separate controllers;
** There are multiple views generated based on the user role (Customer or Manager);
** MongoDB repositories are mapped to Users, Rinks, Groups, and Bookings in the domain model;
** MongoDB provides easy custom queries, indexing, and real time aggregation provide powerful ways to access and analyze data;

* Approval Process:
** Customers first register in the system through an online registration form;
	⁃	User can then create groups that will be attached to bookings;
	⁃	However, as required in the design problem, to make the first booking, a registered user must first get approval by the arena manager;
	⁃	A notification is sent to the manager account, who can approve or not approve the request;

	⁃	Calendar:
	⁃	The calendar is a scheduling UI that provides different views for different user roles and generated through an object factory;
	⁃	
	⁃	Groups:
	⁃	Users can create groups in the system that represent a party of rink users, such as a hockey team, or a class of skating students (up to a maximum size);
	⁃	The registered user who is the administrator of that group and manage its membership;
	⁃	They an delete the group and cancel its bookings. Group administrators can also review all of their bookings;
	⁃	Note that groups represent users of the rink, but not necessarily users of the system. 
	⁃	The advantage of this approach is that a single registered user, such as a coach or booking manager, can book on behalf of multiple groups (e.g. teams in a league). 
	⁃	This optional membership entails rink usage is not restricted to registered members. 
	⁃	This approach supports scenarios in which the arena needs to book rinks for general public use (or for special events) and so expects an unknown number of drop-in users.
	⁃	
	⁃	Booking Process:
	⁃	To book a rink, users first view available time slots for different rinks on a calendar;
	⁃	Once a time slot is selected, the user submits the booking and the calendar shows the time slot as unavailable to other users;
	⁃	Managers also have the ability to make special bookings, such as to schedule rink maintenance or to block in time for public use of rink facilities;
	⁃	
	⁃	ReactJS: 
	⁃	Simplicity and easy to learn: Not a domain-specific language; component-based approach, well-defined lifecycle, and use of just plain JavaScript make React very simple to learn 
	⁃	Native Approach


	1.	Modularity - A new user group type (e.g. SchoolGroup) can be added. RM: within 2 days.
	2.	Performance - Rink calendar is able to calculate free time. RM: in under 30s.
	3.	Security - Only Managers can cancel rink bookings, not other users. RM: at any time. 

### Adaptability

Our system is designed to be extensible, and hence adaptable for use at facilities other than ice rink arenas, such as other recreational facilities through a generic extensible "facility" class. It also allows the extension of different user roles, for example, various staff roles that require restricted permissions.
Suitability

The system's searchable Calendar provides at-a-glance functionality for users (and in particular managers) to check the availability of rinks for any period of time, and for managers to schedule rink maintenance. It is also suited for managing notifications to large groups.
Learnability
The system is designed for minimal staff training, and provides customers with a clean and simple user interface.

###Changeability

The system's booking service can be extended to handle different kinds of rink bookings, beyond group reservations and scheduled maintenance, such as special events, or "non-ice" events that use the rink. Group types can be extended to the system to handle special booking rates for different uses of the rink.
