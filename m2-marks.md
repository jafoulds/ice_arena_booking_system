
# Marking Guide   26 / 30


## Structural design 7/10
- please define a key in the diagram, even if it is just a line "UML2". Consider this diagram 10 years from now, excavated by some hapless intern with no context.
- the design here does not seem to match up with all of the design patterns discussed in the rationale. Where are the Observers? 
- This diagram should show some of the Spring modules you will use. E.g., how is data being persisted? Where are the controllers that route web requests? Could you hand this to another team and have them write code immediately (my sense is 'not easily'). Remember the module view should be an implementation view, showing what modules exist in an IDE.

## Runtime design 5/5
- much simpler to use this to understand the implementation than the structural model. Nice work.
- "View" is actually multiple sub-views, correct? Do they map 1-1 with controllers? 
- there doesn't seem to be any data flowing back from the services. 


## Allocation views 4/5
- some of the use cases map to one implementation class. Think carefully about how this fits with our idea of CRC cards and responsibilities.

## Rationale  10/10
- Good discussion of quality attributes, although a little vague on how it will all be handled
- Design patterns seems relevant and appropriate to the problem.

