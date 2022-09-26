## Branches
1. mock-all-the-things - tests that makes development more difficult due to mock abuse.
   1. Start: Add tests suites
      1. Test for each class - too many and make it unsafe to refactor.
      2. PresentationDtoFactoryTest - no tests for handling exceptions.
      3. PresentationFactoryTest - test that supports not existing scenario when DTO is null.
      4. ConferenceTest - we do not validate Presentation creation.
   2. Method contract changes: Presentation.scheduledForSlot throws exception
      1. Unit Tests added.
      2. What about support in Conference?
2. tdd-is-not-enough - tests written in TDD manner with full coverage but without important scenarios
   1. Start at commit: Add tests suite ConferenceTest
   2. Many presentations: Add scenario with many registered presentations
   3. Parametrized tests with presentation availability: Convert invalid title tests into parametrized test 
3. coverage-as-info - how to use coverage as an information
   1. Start at commit: Add tests suite ConferenceTest
   2. Add missing scenario: Add missing tests to recognize unavailable slot
   3. Remove "impossible code": Remove code impossible to reach
   4. Add missing scenarios: Add missing scenarios for validators
   5. Add unit tests: Add missing unit test for validator