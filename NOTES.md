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
- What it produced: It suggested that start and end being equal should be included, however the spec states **end must be strictly after start**
- What was wrong: A1 states end must be strictly after start.
- The test that caught it: rejectstartequalsend(), however this only occured as the Ai lacked full knowledge of the spec and overlooked my phrasing of A1.
