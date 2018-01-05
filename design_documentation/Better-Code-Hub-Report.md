### Report Results

The following results show our project's overall compliance for maintainable code, according to the [Better Code Hub (BCH) report](https://bettercodehub.com/results/SENG330-17/project-team-6/).

1. Write Short Units of Code *(Satisfied)*
2. Write Simple Units of Code *(Satisfied)*
3. Write Code Once *(Satisfied)*
4. Keep Unit Interfaces Small *(Satisfied)*
5. Separate Concerns in Modules *(Satisfied)*
6. Couple Architecture Components Loosely *(Satisfied)*
7. Keep Architecture Components Balanced (Satisfied)
8. Keep Your Codebase Small *(Satisfied)*
9. Automate Tests  *(Not Satisfied)*
10. Write Clean Code *(Satisfied)*


### RESPONSE
Our codebase scored 9/10 overall, with only one maintainability aspect failure: Automated Tests. We agree with this result, and feel it reflects our efforts to maintain a codebase that is clean, simplified and optimized for the project requirements. Comments related to Automated Tests are below.

For the Automated Test review, BCH calculated 25% of our production code (~360 out of 1,500 lines of production code) had automated tests, with a 7% Assert density. Note, however, that our application is only just inside the medium-sized app bracket (where small is defined as < 1,000 lines of code whereas medium is defined as <10,000 LoC). BCH therefore recommends the total lines of test code should be at least 50% of the total lines of production code, and the assert density (percentage of lines of test code containing assertions). But for small systems, BCH only recommends at least some test code and one assertion (currently only checked for Java and C# systems).

To prioritize our time, we focused on building automated tests for our main controller classes (see [codebase tests](https://github.com/SENG330-17/project-team-6/tree/master/src/test/java/com/sealteam6)) - specifically for Booking, User Registration, and Calendar views, as well as for the CalendarFactory class, which was a complex and critical component of the application. 

Certainly, comprehensive automating testing was a implementation goal. Some of the obvious benefits are that automated tests can be run quickly and effectively, are cost effective, and the results are viewable by the whole team. As well, as stated in the Code Hub guidelines, automating tests for the codebase makes development more predictable and less risky. However, given the time constraints of the project, automated tests were not consistently integrated in the general development process for all components (domain, service, repository, etc.).

On the other hand, the team did employ other forms of testing, including extensive manual unit testing (or, for instance, manual URI calls or HTML templates). Manual testing has the benefit of flexibility and can sometimes find real user issues that do not show up in the automated tests. A preferable practice would have been to translate useful manual tests into automated tests as development proceeded.



