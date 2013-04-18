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

`git checkout -f exercise-1`

The goal here is to cover the main functionalities of Jasmine testing framework.
This goes from very basic tests to custom matchers, spies and stubs.

Complete the exercises in ascending order, contained in `src/test/javascript/`.
An example of fixtures (not part of Jasmine core) is given through the two next exercises.

_tips_

 1. run `mvn`
 1. feel free to consult the online documentation: http://pivotal.github.io/jasmine/



#### Exercise 2 - pseudo-compilation with JSLint (15 minutes)

`git checkout -f exercise-2`

Let us discover how JSLint works and can be configured in several ways to adapt its checking and convention rules.
A dummy "JSLint-invalid" module `src/main/webapp/resources/scripts/modules/fixMe.js` has been added for the exercise.
Configuration should also exclude third-party libraries and allow some common global variables (globally on
a per-file basis, that's up to you).

_tips_

 1. run `mvn`
 1. global conf: `<excludes><exclude>path/</exclude><exclude>path\</exclude></excludes>` and `<predefinedVars>a,b,c</predefinedVars>` are your friends
 1. on a file-basis conf, in header:
`/*global globalVar1:true,
globalVar2:true*/`

#### Exercise 3 - autofocus module (15 minutes)

`git checkout -f exercise-3`

HR department has fallen in love with this new tracking tool. 
However, in order to save **hours** of works, the first fields of the administration forms should automatically get the focus. 

Time to complete your first RequireJS module: `src/main/webapp/resources/scripts/modules/autofocus.js`
The minimal specs are defined by a Jasmine test suite in `autofocusTest.js`.

_tips_

 1. see http://api.jquery.com/focus/
 1. and http://underscorejs.org/#first

##### Bonus

Write the test covering the situation where there isn't any reachable input from the provided start selector.



#### Exercise 4 - anti-self-employment module (60 minutes)

HR noticed a bug in _Zupr-Trackr_: they can declare that an employee contracted for his/her employer. This should not be allowed.


##### 4.1 - Ajax module

`git checkout -f exercise-4_1`

Let us now write a module that, given an employee ID, returns all the possible employer objects and execute a callback on those.
The module to complete lies under `src/main/webapp/resources/scripts/modules/nonEmployers.js`.

_tips_

 1. a backend method exposes JSON under `/employees/{ID}/nonEmployers`
 1. see http://api.jquery.com/jQuery.getJSON/ (favour `$.getJSON(url, callback)` signature for your implementation)
 1. `nonEmployersTest.js` suite should pass when your implementation is done!


##### 4.2 - DOM module

`git checkout -f exercise-4_2`

Now that we've got our Ajax module up and running. It is time to update our form with the fetched data whenever an employee is selected!
The modules lies under `src/main/webapp/resources/scripts/modules/pages/activities.js`.

 1. the module should observe any change to the employee dropdown (`employeeDropDownSelector`)
 1. then, it should get the selected option and call the previously defined module with
 1. finally, the result should replace the company dropdown (`companyDropDownSelector`) contents


Notes:

 - that you should run `mvn test` to play the test suites.
 - if you're NOT using a MAC, please change `<phantomjs.binary.path>` in `pom.xml`.
    - Windows users: phantomjs.exe
    - Linux 32/64: phantomjs_32 or phantomjs_64

_tips_

 1. this new module should call `nonEmployers.fetchCompanies` with its own callback
 1. see http://api.jquery.com/selected-selector/ to get the selected employee option
 1. see http://api.jquery.com/val/ to extract its ID
 1. try to combine http://underscorejs.org/#reduce and http://underscorejs.org/#template to build the new dropdown HTML contents
 1. see http://api.jquery.com/html/ to update DOM element inner contents
 1. `activitiesTest.js` suite should pass when your implementation is done!

### PART II: Backbone JS

See slides ;-)


### PART III: Angular JS

First, make sure [Node.js](http://nodejs.org/download/) is set up on your computer and included in in the default PATH.
Then, you'll need the package called Karma, the Angular test runner. To do so, run the following command (tailor it to your OS):

`
sudo npm install -g karma
`

Likewise, Karma should be included in the default PATH.


#### Pitch

The two first exercises are built upon [Angular-Seed](https://github.com/angular/angular-seed) and therefore needs a web server.
Before starting those exercises, *open a new terminal tab*, execute the following instructions:

`
cd angular-start;
./scripts/web-server.js
`

Finally, open your favourite browser to `http://localhost:[PORT]/app`.

#### Exercise 1 - look ma', it's AngularJS! (10 minutes)

`cd angular; git checkout -f exercise-1`

 1. change `app/index.html` to insert your first Angular expression!
 1. remove `data-ng-app="myApp"` from `app/index.html` and check in your browser: what do you notice? (write it back afterwards)

_bonus_

Let's spice your expression a bit: add / chain some built-in [filters](http://docs.angularjs.org/api/) (see left column, under "filter").



#### Exercise 2 - repeat after me (15 minutes)

`cd angular-start; git checkout -f exercise-2`

 1. time to create your first controller! Edit `app/controllers.js`.
 1. Edit `app/index.html` and iterate on `elements`

_bonus_

Spice it up! In `app/controllers.js`, assign an array of custom objects instead of strings and display their attributes in `app/index.html` (using `.` accessor). If you lack imagination, just set a bunch of articles `{name: "Game", price: 23}`.

_tips_

 1. every array in Javascript has a length attribute, it could be useful to count the number of elements ;)


#### Exercise 3 - welcome back to ZuprTrackr! (20 minutes)

This time, and for good, we're back in ZuprTrackr. As usual, `mvn` will start the app.

`git checkout -f angular-exercise-3`

The CTO of ZuprTrackr is very enthusiastic about AngularJS and would like you to prepare a Proof Of Concept based on the framework.
The goal is to display, very similarly to the previous exercise, some fake data within the homepage.

The homepage URL in this case is a bit different `http://localhost:8080/?framework=angular`.

 1. edit `src/main/webapp/resources/scripts/modules/angularjs/controllers.js` add assign some complex nested data structures to the scope (at least 3 elements). Example:

	 `[{
	    rate: 560,
	    day: "2013-12-09",
	    employee : {
	        firstName: 'Florent',
	        lastName: 'Biville',
	        employer : {
	            name : 'Lateral Thoughts',
	            address : 'PARIS'
	        }
	    },
	    client : {
	        name: 'ACME',
	        address : 'PARIS'
	    }
	}/** add 2 more */]`

 1. adapt `src/main/webapp/WEB-INF/views/home/angular.jsp` so it display some interesting properties of your model.


_tips_

 1. take some time to read RequireJS `src/main/webapp/resources/scripts/main.js` configuration file and `src/main/webapp/resources/scripts/modules/angularjs` package to better understand how to fit AngularJS into an AMD environment.

 1. notice how the unprocessed contents of `angular.jsp` is displayed some millisecond before the real contents? Try to add `data-ng-cloak` attribute (no value necessary) to the `div` next to "hero-unit" div. Better? :-)




#### Exercise 4 - let's sort them out! (20 minutes)

`git checkout -f angular-exercise-4`

Note: this resets the previous data. You should have now in your model 3 employees.

Now that we've got some fake data to work on, let's make the Angular homepage a bit more dynamic. After, it's all about "2-way data-binding", isn't it?

 1. let's modify `angular.jsp` by adding a simple text input with `data-ng-model="myFilter"` attribute and applying to the collection expression in `data-ng-repeat`.
 1. let's declare a more complex filter in `src/main/webapp/resources/scripts/modules/angularjs/filters.js` that'll filter on employee first name OR last name. Adapt `angular.jsp` to this new filter.

_tips_

 1. the custom filter shouldn't do an exact match, call `indexOf` instead!
 1. it should also be case-insensitive (favour `toLocaleLowerCase` to `toLowerCase` - think about our Turkish friends!)


#### Exercise 5 - plug it! (20 minutes)

`git checkout -f angular-exercise-5`

Time to plug our backend to AngularJS. But first, we should start splitting our app in several views.

 1. just check `angular.jsp` to see what's changed. Now remains the routing part: complete `app.js` to add the missing routes.
 1. before being able to contact the backend, you need to define RESTful services in `services.js`
 1. finally, assign the data to the $scope in `controllers.js` by calling the services you defined in the previous step (`$scope.elements = myService.query()`)

### PART IV

See slides ;-)

