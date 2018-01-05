In the following we present a short retrospective (postmortem) on the project to highlight areas of success and areas where it fell short of the expectations and requirements.

## Contents

* [Group dynamics and communication](#group-dynamics-and-communication)
* [Requirements](#requirements) 
* [Design](#design) 
* [Implementation](#implementation) 
* [Deployment](#deployment) 

## Report

### Group dynamics and communication

Overall, our group worked well as team. Each team member had a well-defined role within the group and for the most part, each group member fulfilled what they were responsible for. Early on in the project, a lot of time went into the discussion of the design of the entire project. Each member had strong opinions on certain topics, so each final design decision that was made throughout the project was well thought out. We think this is what led to a system design that thoroughly fulfilled the customer's requirements. 

Communication was a strong point of group. All of the group’s members heavily used Slack to communicate with other members whether it was to ask someone’s opinion or to ask for help with some coding. No group member was afraid to express their opinion on a topic and no one within the group was never malicious to another group member. 

### Requirements

In general, the basic customer requirements were implemented in the application, with core user, booking and calendar functionality made available through a simple UI. The primary requirements not implemented before the deadline were those detailed in the manager, and some of the group, [use cases](https://github.com/SENG330-17/project-team-6/wiki/Requirements-Breakdown). 

A key obstacle to implementing these use cases was that user permissions were not fully in place to set manager privileges and group membership. This obstacle was due, in part, to difficult codebase merge conflicts that could not be resolved before the deadline. As a result, we did not have the kind of fine-grained permissions control assumed in the range of operations outlined in our manager and group use cases. User permissions were a core component of the design, rather than the result of scope creep.

#### Manager Functionality
Functioning permission settings would have allowed managers to approve group registrations; create and cancel special bookings (e.g. scheduled and unscheduled rink maintenance and public use bookings). As well, managers would have had expanded calendar views that included all user bookings. 

#### Group Functionality
User permissions were also necessary component for transferring ownership of groups, and for users to control group membership. Each user in a group was to have a permission level for that group specifying the actions they can perform.

#### Invoicing and Notifications
Two lesser requirements not implemented in time were group invoicing and group notifications. Group notifications were added as a secondary features of the design (a "nice-to-have" feature), though not listed in the requirements breakdown. Invoicing, on the other hand, was part of the design problem, but not initiated as more fundamental functionality took precedence.

The following is a table of [Project Requirements](https://github.com/SENG330-17/project-team-6/wiki/Requirements-Breakdown) that expands on some of the use cases:

| Requirement  | Implementation |
| ------------- | ------------- |
| Register New User  | *Implemented to specifications*  |
| User Login  | *Implemented to specifications*  |
| Book Rink (Customer/Manager)  | *Implemented to specifications*  |
| Cancel Booking (Customer/Manager) | *Implemented to specifications* |
| Register Group (Customer) |  *Partially implemented* (Users able to create and delete groups.) |
| Approve Group Registration (Manager) | *Not implemented*  |
| Add User to Group (Customer) | *Not implemented*  |
| Transfer Ownership of Group (Customer) | *Not implemented*  |
| Review Bookings Calendar (Customer)  | *Implemented to specifications* |
| Review Bookings Calendar (Manager)  | *Not implemented* |
| Schedule Rink Maintenance/Special Booking (Manager) | *Not implemented* |
| Invoice Customer (Customer)  | *Not implemented* |


### Design

During implementation, we followed the design document quite closely, and in particular the updated versions of our [Detailed Structural Model](https://github.com/SENG330-17/project-team-6/wiki/Detailed-Structural-Model) and [Requirements](https://github.com/SENG330-17/project-team-6/wiki/Requirements-Breakdown). However, as shown in the Requirements table above, as we began to implement some of our use cases, new user scenarios emerged that required modifications to our requirements. For example, it became apparent that handling user permission levels was an important issue in itself, rather than implied by handling the manager or group membership use cases.

In addition, we introduced some additional classes to the design:

* *User authentication*: Handled by Spring Framework's in-built UserDetails core interface which loads user-specific data.
* *Service and Data-Access Layers*: We introduced abstraction of the business logic and data access. It seemed prudent to encapsulate User, Group, Calendar and Booking logic into service classes, and limit the responsibilities of controllers to request/response handling. Data access was further passed to repository classes that provided a simplified mongodb data-access layer.
* *ArenaScheduleService class*: We introduced a separate class to set the operating hours of the arena, and offered a possible way for managers to program the calendar.
* *Deserializer extension*: We had to introduce a deserializer subclass to support the tricky processing and conversion of JSON and Java date-time strings. It proved the cleanest approach that made good use of Jackson abstract class to deserialize date objects from JSON, using provided JsonParser.

An area that, in retrospect, deserved considerably more design attention was the User Interface. Granted UI design was not a primary objective of the project, our UI progressed according to the needs of the prototype implementation, and therefore developed on an ad hoc basis. The result was a minimal interface that did not give adequate consideration to usability, but merely reflected the functional requirements. Moreover, a more structured design of the ReactJS components would have reduced development time, improved component composition and facilitated common abstraction. In short, UI design would have streamlined implementation of the app's interactive features.

### Implementation

For our back end we used Spring Boot. This turned out to be a good choice as there was very little configuration required which sped up development time. We ended up opting for a fairly anemic domain model, as it is implicitly encouraged by Spring by making it difficult to use Spring Beans in the domain model. 

For our front end we used React. We found is relatively difficult to set up the integration between the front end and back end with React. In retrospect, it may have been better to separate them entirely instead of shoehorning them into the same project. 

Learning React and Spring Boot had a higher time requirement than initially anticipated and delayed our progress substantially. Cameron had previous React experience, but the rest of us had not worked with either framework before so there was a steep learning curve. We think that ultimately, we should not have used React because while it led to a more maintainable front end and faster development once understood, the learning curve counteracted those benefits.

### Deployment

We ran into several challenges while preparing for our presentation, and getting the app deployed. Issues with time conflicts, merge conflicts, AWS deployment, and presentation preparation were notable.

#### 1. Time Conflicts
Naturally with most group work, there was some difficulty with timing. Since the project was due towards the end of semester, many of us were overloaded with other things to focus on. Consistent work had been done, but since members had different times of freedom to work, we would work at different times. Working independently instead of collectively was good for efficiency, but caused issues with merge conflicts.

#### 2. Merge Conflicts
Merge Conflicts were the main issue in getting our project ready for the presentation. Earlier on, we had created separate branchs for different aspects of the project, in specific the newGroups branch. A change to the way the groups were stored was made, which caused conflicts for earlier code written. Even though Cameron's group branch was up to date with master, his database did not have any rinks, he was not able to see the issue. The merge later on revealed the issue, and we did not have time to try update everything to be ready for the presentation. This meant we had to leave out all the code Cameron wrote. In retrospect, we should have merged the changes to the group object right away to make sure the conflict never existed. It also would have helped to make sure we had a standard testing database that everyone tested their branch on. Cameron made his own testing database which failed to catch the errors we saw when using the database used by Ben. 

#### 3. Deployment On AWS (Amazon Web Services)
We attempted to deploy our app on AWS to have a way to access it online. Cameron had previous experience deploying on AWS, however not with a React / Spring boot application. We attempted to run it as we would normally run the service on our own machine. This approach failed, and would have required a lot of work to make work, so we discarded the idea.

#### 4. Presentation
Finding a proper place to practice for the presentation was challenging. Every room we found that had a projector we would get kicked out of for a class. This made getting a time estimate difficult, and practising slide progression challenging.

