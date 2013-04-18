## Exercises

### PART I: INDUSTRIALIZATION

For most exercises, there is a corresponding Jasmine test suite.
All you have to do is write enough code to make the unit tests pass.

Read the exercises carefully and do not hesitate to check the tips.

#### Pitch

Zupr-Trackr is a Spring Data/JPA toy project.
It allows employee contracting tracking.

Moreover, the application exposes a REST API through Spring Data/REST repository discovery mechanism (see http://localhost:8080/api).

#### Getting started

Just run...

`mvn`

... to compile, run unit (Java && JS) tests and start an embedded Tomcat server.
Note this app embeds in-memory H2 database. Data will be erased at each new start (if you run `clean` goal).

#### Exercise 1 - the crescendo suite (45 minutes)

`git checkout exercise-1`

The goal here is to cover the main functionalities of Jasmine testing framework.
This goes from very basic tests to custom matchers, spies and stubs.

Complete the exercises in ascending order, contained in `src/test/javascript/`.
An example of fixtures (not part of Jasmine core) is given through the two next exercises.

_tips_

 1. run 'mvn'
 1. feel free to consult the online documentation: http://pivotal.github.io/jasmine/



#### Exercise 2 - pseudo-compilation avec JSLint (15 minutes)

`git checkout exercise-2`

Let us discover how JSLint works and can be configured in several ways to adapt its checking and convention rules.

_tips_

 1. run `mvn`
 1. global conf: `<excludes><exclude>path/</exclude><exclude>path\</exclude></excludes>` and `<predefinedVars>a,b,c</predefinedVars>` are your friends
 1. on a file-basis conf, in header:
`/*global globalVar1:true,
globalVar2:true*/`

#### Exercise 3 - autofocus module (15 minutes)

`git checkout exercise-3`

HR department has fallen in love with this new tracking tool. 
However, in order to save **hours** of works, the first fields of the administration forms should automatically get the focus. 

Time to complete your first RequireJS module. The minimal specs are defined by a Jasmine test suite in `autofocusTest.js`.

_tips_

 1. see http://api.jquery.com/focus/
 1. and http://underscorejs.org/#head

##### Bonus

Write the test covering the situation where there isn't any reachable input from the provided start selector.



#### Exercise 4 - anti-self-employment module (60 minutes)

`git checkout exercise-4_1`

HR noticed a bug in _Zupr-Trackr_: they can declare that an employee contracted for his/her employer. This should not be allowed.


##### 4.1 - Ajax module

Let us now write a module that, given an employee ID, returns all the possible employer objects and execute a callback on those.

_tips_

 1. a backend method exposes JSON under `/employees/{ID}/nonEmployers`
 1. see http://api.jquery.com/jQuery.getJSON/ (favour `$.getJSON(url, callback)` signature for your implementation)
 1. `nonEmployersTest.js` suite should pass when your implementation is done!


##### 4.2 - DOM module

`git checkout exercise-3_2`

Now that we've got our Ajax module up and running. It is time to update our form with the fetched data whenever an employee is selected!

_tips_

 1. this new module should call `nonEmployers.fetchCompanies` with its own callback
 1. see http://api.jquery.com/selected-selector/ to get the selected employee option
 1. see http://api.jquery.com/val/ to extract its ID
 1. try to combine http://underscorejs.org/#reduce and http://underscorejs.org/#template to build the new dropdown HTML contents
 1. see http://api.jquery.com/html/ to update DOM element inner contents
 1. `activitiesTest.js` suite should pass when your implementation is done!

