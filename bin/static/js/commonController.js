var dynamicFormsApp = angular.module('dynamicFormsApp', [ 'ngRoute' ,'angular-growl']);

// configure our routes
dynamicFormsApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : 'partials/mainContent.html',
		controller : 'mainController'
	})
	
	.when('/company',{
		templateUrl : 'partials/company.html',
		controller : 'CompanyController'
	})
	
	.when('/project',{
		templateUrl : 'partials/project.html',
		controller : 'ProjectController'
	})
	.when('/form',{
		templateUrl : 'partials/form.html',
		controller : 'formController'
	})
	
	;
});

// create the controller and inject Angular's $scope
dynamicFormsApp.controller('mainController', function($scope) {
	// create a message to display in our view
	$scope.message = 'Everyone come and see how good I look!';
});

dynamicFormsApp.controller('CompanyController', ['$scope', 'CompanyService','growl', function($scope, CompanyService,growl) {
    var self = this;
    self.company={id:null,name:'',description:''};
    self.companies=[];
    
    self.createCompany = function(newCompany){
    	CompanyService.createCompany(newCompany)
    	.then(function successCallback(response) {
		    growl.success(response["data"].message);
		  }, function errorCallback(response) {
			  growl.error("Error creating company!!");
		  });
    };
    
    self.submit = function() {
        if(self.company.id===null){
            self.createCompany(self.company);
        }else{
            self.updateCompany(self.company, self.company.id);
        }
    };
    
}]);

dynamicFormsApp.controller('ProjectController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});

dynamicFormsApp.controller('formController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});





//$scope.showWarning = function(){
//    growl.warning('This is warning message.',{title: 'Warning!'});
//}
//$scope.showError = function(){
//    growl.error('This is error message.',{title: 'Error!'});
//}
//$scope.showSuccess = function(){
//    growl.success('This is success message.',{title: 'Success!'});
//}
//$scope.showInfo = function(){
//    growl.info('This is an info message.',{title: 'Info!'});
//}
//$scope.showAll = function(){
//    growl.warning('This is warning message.',{title: 'Warning!'});
//    growl.error('This is error message.',{title: 'Error!'});
//    growl.success('This is success message.',{title: 'Success!'});
//    growl.info('This is an info message.',{title: 'Info!'});
//}

















/*
 * sampleApp.factory('dataFactory',['$http',function($http){ var dataFactory =
 * {}; var baseURL ="http://localhost:8181/AngularJSProject/rest/test";
 * 
 * dataFactory.getData = function(){
 * 
 * console.log("inside Get Data function")
 * 
 * var req = $http({ method: 'get', url: baseURL, data: JSON.stringify({'name' :
 * 'Amol', 'value': 'pujari'}), dataType: 'jsonp'
 * 
 * });
 * 
 * req.success(function(response){ alert("success callback");
 * console.log("Success = " + response); });
 * 
 * req.error(function(response){ alert("error callback"); console.log("error = " +
 * response); }); };
 * 
 * return dataFactory; }]);
 * 
 * 
 * 
 * 
 * sampleApp.controller('ngTextFieldController',['$scope','dataFactory',function($scope,dataFactory){
 * $scope.callRestServer = function(methodName){ alert('test');
 * dataFactory.getData(); /* var responsePromise =
 * $http.jsonp("http://localhost:8181/AngularJSProject/rest/"+methodName);
 * responsePromise.success(function(data){ alert("http response = " + data); });
 * responsePromise.error(function(data){ alert("Error = '" + data + "'"); });
 */
/*
 * }; }]);
 * 
 * 
 * 
 * 
 * sampleApp.config(['$routeProvider', function($routeProvider){ $routeProvider
 * .when('/login',{ templateUrl: 'templates/login.html',
 * controller:'LoginController' }).when('/signup',{ templateUrl:
 * 'templates/signUp.html', controller:'SignUpController' }).otherwise({
 * redirectTo: '/' }) }]) ;
 * 
 * 
 * sampleApp.controller('SignUpController',['$scope','dataFactory',function($scope,dataFactory){
 * 
 * $scope.signUp = function(){ alert($scope.homeValue); alert("Thank you for
 * signing up!!"); }
 * 
 * }]);
 * 
 * sampleApp.controller('LoginController',['$scope','$location',function($scope,$location){
 * 
 * $scope.login = function(){ if($scope.username == "amol" && $scope.password ==
 * "123"){ alert("Successfully logged in!!");
 * 
 * }else{ alert("Login failed!!"); $location.path("/signup"); } }
 * 
 * }]);
 * 
 * sampleApp.controller('LeftPanelController',['$scope',function($scope){
 * 
 * $scope.components = [ { id: 1, name:"Title Bar", imageSrc: "image1.jpg",
 * description:"Adds title bar on the page",
 * templatePath:"'components/TitleBar.html'" }, { id: 2, name:"Footer",
 * imageSrc: "image2.jpg", description:"Adds footer on the page" }, { id: 3,
 * name:"Content", imageSrc: "image3.jpg", description:"Adds content area on the
 * page" } ];
 * 
 * $scope.addComponent = function(obj){ var searchVal =
 * obj.target.attributes.id.value; alert(obj.target.attributes.id.value);
 * 
 * 
 * var component = jQuery.grep($scope.components, function(obj,idValue) {
 * if(obj.id === idValue){ return obj; }else{return null;} });
 * 
 * 
 *  }
 * 
 * 
 * 
 * }]);
 * 
 * sampleApp.controller('ContentController',['$scope',function($scope){
 * 
 * $scope.layoutPath = "'components/layouts/layout1.html'";
 * $scope.titleBarTemplate.url = "'components/TitleBar.html'"; }]);
 * 
 */

