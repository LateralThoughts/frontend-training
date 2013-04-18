describe("spies", function() {

    beforeEach(function() {
        object = {
            method: function(val) {
            },
            otherMethod: function() {
                return "hello ya!";
            },
            yetAnotherMethod: function() {
            }
        }
        //TODO: uncomment and adapt the following lines to make the tests pass
        //spyOn(myObj, "myFunc");
        //spyOn(myObj, "myFunc").andCallThrough();
        //TODO
        //actually do some calls

    });

    describe("basic spies", function() {
        it("should check if a function is called", function() {
            expect(object.method).toHaveBeenCalled();
        });

        it("should check how many times a function has been called", function() {
            expect(object.method.calls.length).toBe(2);
        });

        it("should check the passed arguments", function() {
            expect(object.method).toHaveBeenCalledWith(42);
            expect(object.method.mostRecentCall.args[1]).toEqual(4224);
        });
    });

    describe("more sophisticated spies", function() {
        it("should spy and get it executed", function() {
            expect(object.otherMethod).toHaveBeenCalled();
            expect(object.otherMethod()).toMatch("hello");
        });

        it("should spy and get the fake result", function() {
            // WARNING: it is forbidden here to modify
            // ... object.method implementation
            expect(object.method).toHaveBeenCalled();
            expect(object.method()).toBe(42);
        });

        it("should spy and execute the fake implementation", function() {
            // SAME WARNING: it is forbidden here to modify
            // ... object.yetAnotherMethod implementation
            expect(window.s95).not.toBeDefined();
            expect(object.yetAnotherMethod()).toBe(42);
            expect(window.s95).toBe(false);
        });
    })
});