define(["jquery", "modules/nonEmployers"], function($, nonEmployers) {
    describe("non-employer test suite", function() {

        it("should contact the correct URL", function() {
            spyOn($, "getJSON");
            nonEmployers.fetchCompanies(1, function() {});
            expect($.getJSON).toHaveBeenCalledWith("/employees/1/nonEmployers", jasmine.any(Function));
            expect($.getJSON.mostRecentCall.args[0]).toEqual("/employees/1/nonEmployers");
        });

        it("should execute the callback", function() {
            var getJSONReplacement = function(url, callback) {
                return callback([]);
            };
            spyOn($, "getJSON").andCallFake(getJSONReplacement);
            var callback = jasmine.createSpy();
            nonEmployers.fetchCompanies(1, callback);
            expect(callback).toHaveBeenCalledWith([]);
        });
    });
});