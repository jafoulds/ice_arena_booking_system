## Contents

* [Highlights](#highlights)
* [Quality Attributes](#quality-attributes)  
* [Design Patterns](#design-patterns) 

## Highlights

The following are the key design decisions that will guide implementation.

### Simplified Registration (Approval) Process

During regular operation, the proposed booking system allows registered customers to book rinks for group use. Customers first register in the system through a simple online form. However, as required in the design problem, to make the first booking, a registered user must first get approval by the arena manager through a separate online application and confirmation process. 

Manager users control which registered users get to book rinks. This satisfies the proposal requirement that groups must first apply to the arena before they are allowed to complete their first booking. Managers also have the ability to make special bookings, such as to schedule rink maintenance.

### Single User - Multiple Groups

Following approval, the user can create groups in the system that represent a party of rink users, such as a hockey team, or a class of skating students (up to a maximum size). The registered user who is the creator of the group is called the "group administrator," and he or she manages the size and membership of the group, and can delete the group and cancel its bookings. Group administrators can also review all of their bookings.

### Optional Group Membership

Note that groups represent users of the rink, but not necessarily users of the system. Specifically, group members are not required to register in the system, but can be considered anonymous. Any (non-registered) group member can optionally register and join groups with approval from the group administrator. The incentive for registration is that the system has functionality to automatically notify group members of new and upcoming bookings, as well as changes to group bookings. 

The advantage of this approach is that a single registered user, such as a coach or booking manager, can book on behalf of multiple groups (e.g. teams in a league). This optional membership entails rink usage is not restricted to registered members. This approach supports scenarios in which the arena needs to book rinks for general public use (or for special events) and so expects an unknown number of drop-in users.


## Quality Attributes

We have identified the following quality attributes of our software design, based on the quality model presented in the first part of the standard, ISO/IEC 9126-1.

### Adaptability

Our system is designed to be extensible, and hence adaptable for use at facilities other than ice rink arenas, such as other recreational facilities through a generic extensible "facility" class. It also allows the extension of different user roles, for example, various staff roles that require restricted permissions. 

### Suitability

The system's searchable Calendar provides at-a-glance functionality for users (and in particular managers) to check the availability of rinks for any period of time, and for managers to schedule rink maintenance. It is also suited for managing notifications to large groups.

### Learnability

The system is designed for minimal staff training, and provides customers with a clean and simple user interface.

### Changeability

The system's booking service can be extended to handle different kinds of rink bookings, beyond group reservations and scheduled maintenance, such as special events, or "non-ice" events that use the rink. Group types can be extended to the system to handle special booking rates for different uses of the rink. 


## Design Patterns

The following are design patterns used in the development of this design.

### Observer Pattern

Although group registration is not required to use a rink, user registration and group membership have the optional benefit of automated user notifications about changes to relevant group bookings. By using the Observer Pattern to attach group members to bookings, all group members are notified and updated automatically.


### Factory Pattern

Calendar views can be tailored for different users through a CalendarFactory class used to generate concrete calendar views. This limits Customer booking information to his or her own bookings, and, if requested, currently available ("bookable") rinks for a given date/time query. The managers calendar view shows all of the bookings (including scheduled maintenance bookings), with the additional functionality to edit or cancel bookings.

### Model-View-Controller Pattern

We will be using the Spring MVC framework, which is essentially an implementation of the Model-View-Controller pattern. The main motivation behind using this design pattern is that it divides our system into three logical components which each have a specific role and provides flexibility for future changes. This pattern also facilitates the requirement of being able to provide a wide variety of views of the booking calendar to the user.