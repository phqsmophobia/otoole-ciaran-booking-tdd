# NOTES

## Gaps

| Rule | What was unclear | Ruling | My test for it |
| ---- | ---------------- | ------ | -------------- |
| R?   |                  |        |                |

## AI corrections (at least three)

### 1 TDD Workflow Pattern
- What I asked: I asked if i needed to create templates of the methods in advance.
- What it produced: AI suggested adding them even though it was aware we were doing TDD and tests for them were not written.
- What was wrong: No tests had been generated for these methods and the AI was jumping way ahead in steps, violating TDD.
- The test that caught it: No specific test, I identified the error as it went against TDD workflow.


### 2 Incorrectly Suggested A Gap
- What I asked: I was discussing the notes formatting gaps.
- What it produced: It suggested that start and end being equal should be included, however the spec states **end must be strictly after start**.
- What was wrong: A1 states end must be strictly after start.
- The test that caught it: rejectstartequalsend(), however this only occured as the Ai lacked full knowledge of the spec and overlooked my phrasing of A1.


### 3 Assisted in creating Equals
- What I asked: I am struggling with the logic of the equals method, could you help?
- What it produced: It suggested code that compares two TimeSlot objects using their start and end times.
- What was wrong: nothing was wrong with the suggested implementation.
- The test that caught it: It was required to pass the testequaltimeslots() test.

### 4 Assisted in creation of equals in booking class
What I asked: I asked for help understanding why my bookingAFreeRoomReturnsTheBooking() test was failing when comparing the two objects.  \
What it produced: It suggested overriding the equals() method in Booking.java to compare the userId, roomId and slot fields and generated the code.  \
What was wrong: Java's default equals() method compares object identity rather than the contents of the two bookings.  \
The test that caught it: bookingAFreeRoomReturnsTheBooking() in BookingServiceTest.java.
