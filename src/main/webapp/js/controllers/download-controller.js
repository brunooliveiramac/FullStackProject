angular.module('guaraniApp').controller('DownloadController', function($scope, $http, $location, ParamService, FileService, $window) {


	$scope.layouts = [
	 {name:'LayoutV66.doc'},
	 {name:'LayoutV67.doc'},
	 {name:'LayoutV68.doc'},
	 {name:'LayoutV69.doc'},
	 {name:'LayoutV70.doc'}
	 ];
	
	$scope.layoutList = $scope.layouts[0];
	 
	$scope.getFile = function(){
    
	$window.open(FileService.getFile($scope.layoutList.name));

	console.log($scope.layoutList.name);
		
	};
	  
	

}); 