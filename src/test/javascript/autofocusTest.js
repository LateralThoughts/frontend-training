define(["jquery", "modules/autofocus"], function($, autofocus) {
    describe("Autofocus test suite", function() {
        var $form, $firstVisibleInput;
        beforeEach(function() {
            this.addMatchers({
                toHaveFocus: function() {
                    // do not use :focus - https://code.google.com/p/phantomjs/issues/detail?id=427
                    return $(this.actual).get(0) == document.activeElement;
                }
            });
            $form = affix('form[id=testForm]');
            $firstVisibleInput = $form.affix('input[id=first]')
        });
        it("should focus on form first input", function() {
            autofocus.focus("#testForm");
            expect($firstVisibleInput).toHaveFocus();
        });
    });
});