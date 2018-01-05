The following expands on the use cases, scoping, and assumptions presented in Assignment #1 to develop initial project tasks.

## Assumptions

### Rinks
* There is only one arena complex.
* There is different types of rinks
* There is both scheduled and unscheduled maintenance to rinks.
### Groups
* Groups are billed monthly and are not charged for the time they book in the rinks.
* Group can contain multiple users who can be added and removed at any time
* Each user in a group has a permission level for that group specifying the actions they can perform

## Use Cases

* [Use Case 1: Book a Rink](#use-case-1-book-a-rink)
* [Use Case 2: Register a New User](#use-case-2-register-a-new-user) 
* [Use Case 3: Register a Group](#use-case-3-register-a-group) 
* [Use Case 4: Review Bookings (User)](#use-case-4-review-bookings-user) 
* [Use Case 5: Review Bookings (Manager)](#use-case-5-review-bookings-manager)
* [Use Case 6: Cancel Booking (User)](#use-case-6-cancel-booking-user) 
* [Use Case 7: Schedule Rink Maintenance (Manager)](#use-case-7-schedule-rink-maintenance-manager) 
* [Use Case 8: Confirm a Booking Request (Manager)](#use-case-8-confirm-a-booking-request-manager)  

***

### Use Case 1: Book a Rink

**Primary Actor** - Registered User

**Description** - A registered user selects a rink and books it for a specific time period. Once the rink is booked the system shows a confirmation and the time slot is no longer available to other users.

**Scope** - Booking system.

**Preconditions** - User is registered in the system and logged on.

**Success End Condition** - A booking application is submitted.

**Basic Flow (Success Scenario)**
1. User requests a new rink booking from the system for a specific date and time of use (time slot).
2. System verifies that user is authenticated. 
3. System returns a selection of available rinks for the requested time slot.
4. User selects an available rink.
5. System adds the booking to the database.
6. System adds cost to users monthly bill.
7. System notifies the user that the booking was successful.
8. Available time slots present on the website are updated.

**Alternate Flows**

5a. If no rinks are available for the specified date and time, the system notifies the user and does not create a new booking.

5b. If the requested time exceeds a threshold, the system notifies the user and does not create a new booking. (See Use Case 8).

**Issues**

1. Should the system also have a way for users to see available rinks and select one, rather than query an available rink?

2. Users should be able to book rinks for multiple groups? (e.g. Booking coordinator for league books rinks for multiple teams).

***

### Use Case 2: Register a New User

**Primary Actor** - New User

**Description** - Create a new user account, which can be used book to different rinks (at different times).

**Scope** - User Account System

**Preconditions** - None

**Success End Condition** - User is registered in the system (new account created).

**Basic Flow (Success Scenario)**
1. User completes and submits a registration form that provides user information for creating an account
2. System verifies the user information, handling errors as necessary
3. User is added to the database
4. System sends user confirmation of registration

**Alternate Flows**

2a. [Need details: Handle problems with user input errors]

***

### Use Case 3: Register a Group

**Primary Actor** - Registered User

**Description** - Register a group of users, which can be used in booking different rinks (at different times). NOTE: The user can use this group tag to book rinks, invite other users to join the group, send messages to all members of the group, remove other users from the group, cancel the groups booking.

**Scope** - User Account System

**Preconditions** - User is registered in the system and logged on.

**Success End Condition** - A group is registered in the system.

**Basic Flow (Success Scenario)**
1. User requests to create a new group to the system.
2. System creates a group object, which the user has administrative control of (meaning can add or remove other users and make bookings)
3. System notifies the user that the group has been created
4. The System adds the group to a database 

**Alternate Flows**

**Issues**
1. Should groups require that all of its members be registered users? (i.e. can a user, such as a coach or instructor, register a rink on behalf of a non-registered group?)

***

### Use Case 4: Review Bookings (User)

**Primary Actor** - Registered User

**Description** - Users who are part of one or more groups view the bookings for those groups.

**Scope** - Booking system.

**Preconditions** - User is registered in the system and logged on. User is part and/or the owner of a group.

**Success End Condition** - User is shown availability of rinks for a selected time period and group.

**Basic Flow (Success Scenario)**
1. Given a user is part of a group, that user can select any booking that group is in
2. If the user selects a booking, the system will retrieve that booking from its database
3. System returns and displays details about that booking  

**Alternate Flows**

3a. If no bookings exist for the user, the system shows no bookings have been made.

**Issues**
1. Users should be able to be a part of multiple groups?
2. [Needs details: Group users who are not the owner of the group have the option to leave the booking.]
3. [Needs details: Owners of the booking have the option to configure (update?) booking.]

***

### Use Case 5: Review Bookings (Manager)

**Primary Actor** - Manager

**Description** -  Arena manager reviews all scheduled bookings for a selected time period, or for a selected group

**Scope** - Booking system

**Preconditions** - Manager is logged on to the system.

**Success End Condition** - System returns list of bookings for the given query data.

**Basic Flow (Success Scenario)**
1. Manager requests a list of bookings by time period or by group.
2. System returns a list of bookings based on the search criteria.

**Alternate Flows**
2a. If no bookings exist for the user, the system shows no bookings have been made.

**Issues**
1. Users should be able to be a part of multiple groups?
2. [Needs details: Group users who are not the owner of the group have the option to leave the booking.]
3. [Needs details: Owners of the booking have the option to configure (update?) booking.]

***

### Use Case 6: Cancel Booking (User)

**Primary Actor** - Registered User

**Description** - User cancels a confirmed booking.

**Scope** - Booking system.

**Preconditions** - User is registered in the system and logged on. User is part and/or the owner of a group.

**Success End Condition** - User has cancelled the selected booking.

**Basic Flow (Success Scenario)**
1. User requests to view current bookings (See Use Case 4)
2. User selects a booking to cancel
3. System removes booking from database
4. System notifies the users in the group of the cancellation.

**Alternate Flows**
2a. If no bookings exist for the user, the system shows no bookings have been made.

**Issues**

***

### Use Case 7: Schedule Rink Maintenance (Manager)

**Description** - Arena manager sets the beginning date and end date for maintenance of a selected rink.

**Scope** - Booking system (?).

**Preconditions** - Manager is logged on to the system.

**Success End Condition** - Maintenance has been scheduled for a specified rink.

**Basic Flow (Success Scenario)**
1. Manager requests a listing of the rinks.
2. Manager selects the rink they wish to schedule maintenance
3. Manager selects date(s) and time(s) for the maintenance.
4. System verifies that the requested time is available.
5. System adds the scheduled maintenance to the database.
6. System confirms the scheduled maintenance.

***

### Use Case 8: Confirm a Booking Request (Manager) 

**Description** -  Arena manager approves or denies a group's booking request.

**Scope** - Booking system

**Preconditions** - Manager is logged on to the system.

**Success End Condition** - Booking request is approved or denied.

**Basic Flow (Success Scenario)**
1. Manager accesses the system.
3. Manager selects the booking request they wish to approve or deny.
4. Manager approves or denies the booking request.
5. Mystem updates the status of the booking request accordingly.
6. System notifies the group that their request has been approved or denied.

