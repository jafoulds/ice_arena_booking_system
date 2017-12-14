# Marking Guide   27 / 40

# Interim Mark 17/25

# Product Requirements 2/5
This is about meeting the requirements identified in the problem statement.

- You only implemented 2 or 3 use cases. There is no manager function.
- I realize you had merge problems, but you *have* to deliver for a client meeting.

# Product Quality 3/5
This is about the content of the pitch.

- You had a few bugs in your demo. The UI was fine, but nothing amazing.

# Technical Briefing 8/10
This is about the content of the technical portion of the pitch. Every team member should expect to be asked about the technical details of the project. Sample question: "how is this controller wired to this view".

- At least a few of you could explain the design. You seem to get what Spring is doing. You have only deployed locally.
- The database aspect is largely taken care of for you.

# Presentation "Pitch" Marks 4/5
This is about the professionalism and presentation of your pitch and tech brief.

- the slides are boring. As a client, I know the requirements. Focus on the live demo. I **Have** to sit through it - but venture capitalists, other funders, etc. won't bother.
- you did a good job organizing the demo, and while boring, the slides were evidence you had prepared. People seemed to know their roles.

# Code and Design Quality 10/15
Note that you have until Friday before the code quality gets marked. NB: Many of the items here can be 'tested' with BetterCodeHub. In other words, a low BCH score will be a negative signal for me about this aspect.

## BCH Score 9/10
## Comments
### Install
- While I was able to get the Spring application to run on my machine, there seemed to be a problem with mongodb. Ideally, your persistance solution should not be something the user should have to set up/configure.
### Design
- You seem to have followed the Spring framework's structure. Not a lot of wiggle-room there.
- You have also implemented a factory design pattern.
### Code Quality and BCH
- On BCH, you do not have an adequate test-to-source-code ratio.
- Checkstyle reports 438 problems with your code. Most are formatting problems. But some include using ```final``` on your ```rink``` parameter, since it seems to be bound to only one object throughout the applications lifetime.
### Models
- I wouldn't call the Calendar classes models, and as such might be better located with the service classes. The opposite applies to group and user services. While Spring does not make the distinction, it is still a violation of the concepts you learned throughout the course.
### Tests
- You do include tests and explain how to run them.
### Docs
- You have adequate javadoc in your code.
### Overall
- You've done a great job utilizing the concepts you learned in the course within the Spring framework.

# Bonus 0/12.5 (project: 5%)
- There's a ```.travis.yml``` file in the repo root. However, it does not seem to run anything.

# Other comments
In all, we felt it was a bit of a lackluster approach to the project. Whether you just had trouble integrating all the features, or didn't put much time in, I think it could have been more compelling.
