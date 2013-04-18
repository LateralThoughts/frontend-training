define(["jquery", "modules/autofocus"], function($, autofocus) {
    describe("Autofocus test suite", function() {
        var $form, $firstVisibleInput;
        beforeEach(function() {
            this.addMatchers({
                toHaveFocus: function() {
                    return $(this.actual).is(":focus");
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