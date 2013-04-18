define([
    'jquery',
    'modules/nonEmployers',
    'text!tpl/employeeOption.tpl'/*,
    add your own if needed ;) */], function($, nonEmployers, employeeTemplate /* add yours */) {

    var updateCompanies = function(selectedEmployeeId, companyDropDownSelector) {
        nonEmployers.fetchCompanies(selectedEmployeeId, function(data) {
            var newContents = '';
            //TODO
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
            $(employeeDropDownSelector).on("change", function() {
                var selectedEmployeeId;//TODO
                if (selectedEmployeeId > 0) {
                    updateCompanies(selectedEmployeeId, companyDropDownSelector);
                }
            });
        }
    };
});