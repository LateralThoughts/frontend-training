describe("custom matchers - ", function() {

    beforeEach(function() {
        // this is executed before each test run...
        // ... and is very similar to JUnit's @Before
        // ... add your custom setup here
        // ... see also afterEach (like @After)
        this.addMatchers({

            toHaveAsSecondElement: function(expected) {
                // please complete ;)
                // the actual value is: this.actual
                // the expected one is: expected
                return this.actual.length >= 2
                    && this.actual[1] === expected;
            },
            toNeverMatch: function() {
                // here the goal is to display
                // ... a custom error message
                this.message = function() { return "I will never match at all !!!"; }
                return false;
            }
        });
    });

    //nothing to do here, make the test run by completing toHaveFortyTwoAsSecondElement matcher
    it("should successfully determine if the arrays contain 42 at index 1", function() {
        expect([2,42,789]).toHaveAsSecondElement(42);
        expect([42,2,789]).not.toHaveAsSecondElement(23);
    });


    //nothing to do here, make the test run and output toNeverMatch matcher error msg
    //then just disable the test (see below to know how)
    xit("will ALWAYS output your error message ;)", function() {
        expect(undefined).toNeverMatch();
    });

    // DISABLED AREA - just for your info ;)
    // just stick a x in front of a suite or a spec!
    xdescribe("disabled suite & tests area", function() {
        xdescribe("nested disabled spec", function() {
           it("I won't run", function() {
               expect("hello").toBeTruthy();
           });
        });

        xit("I won't run either", function() {
           expect("").toBeFalsy();
        });
    });

});