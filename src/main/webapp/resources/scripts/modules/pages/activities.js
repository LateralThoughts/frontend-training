define([
    'jquery',
    'vendor/underscore',
    'modules/nonEmployers',
    'text!tpl/employeeOption.tpl'], function($, _, nonEmployers, employeeTemplate) {

    var updateCompanies = function(selectedEmployeeId, companyDropDownSelector) {
        nonEmployers.fetchCompanies(selectedEmployeeId, function(data) {
            var template = _.template(employeeTemplate),
                newContents = _.reduce(data, function(string,element) {
                    return string + template({
                        value: element.id,
                        display: element.name
                    });
                }, "<option value='-1'>select a client...</option>\n");
            $(companyDropDownSelector).html(newContents);
        });
    };

    return {
        /**
         * Updates the dropdown identified by companyDropDownSelector
         * with the companies that are non employing the selected employee
         * @param employeeDropDownSelector selector of the employee dropdown
         * @param companyDropDownSelector selector of the company dropdown
         */
        observeEmployees: function(employeeDropDownSelector, companyDropDownSelector) {
            $(employeeDropDownSelector).change(function() {
                var selectedEmployeeId = $(employeeDropDownSelector + " option:selected").val();
                if (selectedEmployeeId > 0) {
                    updateCompanies(selectedEmployeeId, companyDropDownSelector);
                }
            });
        }
    };
});