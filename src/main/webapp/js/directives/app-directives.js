angular.module('guaraniApp')
.directive('dangerButton', function() {
	var ddo = {};
	ddo.restrict = "E";
	ddo.scope = {
		name: '@',
		action: '&'
	};
	
	ddo.template = '<button ng-click="action(projeto)" class="btn btn-danger">{{name}}</button>';
	
	return ddo;
});