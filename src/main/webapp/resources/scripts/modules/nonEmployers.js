define(["jquery"], function($) {

    return {
        /**
         * Retrieves all the companies that do not employs the provided employee
         * @param employeeId ID of the employee
         * @param successCallback callback executed on successful request completion
         * @return matching companies
         */
        fetchCompanies: function(employeeId, successCallback) {
            var url = '/employees/' + employeeId + '/nonEmployers';
            return $.getJSON(url, successCallback);
        }
    };
});