dynamicFormsApp.factory('CompanyService', [ '$http', '$q', function($http, $q) {

	return {

		createCompany : function(company) {
			return $http({
				data : company,
				method : 'POST',
				url : '/company'
			}).then(function successCallback(response) {
				return response;
			}, function errorCallback(response) {
				return response;
			});
		}
	};

} ]);