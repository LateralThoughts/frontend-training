define(["jquery",
    "modules/nonEmployers",
    "modules/pages/activities"], function($, nonEmployers, activities) {

    describe("activities test suite", function() {
        var $form, $employeesDropDown, $companiesDropDown;

        beforeEach(function() {
            $form = affix('form[id=testForm]');
            $employeesDropDown = $form.affix('select[id=employees]');
            $employeesDropDown.affix('option[selected=selected]');
            $employeesDropDown.affix('option[value=1]');
            $companiesDropDown = $form.affix('select[id=companies]');
            $companiesDropDown.affix('option');

            spyOn(nonEmployers, "fetchCompanies").andCallFake(function(employeeId, callback) {
                callback([{id: 42, name: "ACME"}, {id: 100, name: "OUI"}]);
            });
        });


        it("should update the company dropdown", function() {

            $(document).ready(function() {
                activities.observeEmployees('#employees', '#companies');
                $('#employees option[selected=selected]').removeAttr("selected");
                $('#employees option[value="1"]').attr("selected", "selected");
                $('#employees').trigger('change');

                waitsFor(function() {
                    var dropDown = $('#companies').html();
                    return dropDown.indexOf('ACME') > 0 && dropDown.indexOf('OUI') > 0;
                }, 'DOM has never been updated', 500);

                runs(function() {
                    var dropDown = $('#companies').html();
                    expect(dropDown).toContain('<option value="42">ACME</option>');
                    expect(dropDown).toContain('<option value="100">OUI</option>');
                });
            });

        });
    });
});