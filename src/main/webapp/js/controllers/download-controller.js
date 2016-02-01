angular.module('guaraniApp').controller('DownloadController', function($scope, $http, $location, ParamService, FileService, $window, $routeParams, FileServiceApk) {

    $scope.layouts = [];

   /*$scope.layouts = [
	 {name:'LayoutV66.doc'},
	 {name:'LayoutV67.doc'},
	 {name:'LayoutV68.doc'},
	 {name:'LayoutV69.doc'},
	 {name:'LayoutV70.doc'}
	 ];*/
    
     $scope.select =  function(){
   
    	 var sliced = $scope.layout.gua_lay_num_ver;

         $scope.doc = ' - Documentação Versão '+ sliced;
         $scope.apk = ' - APK Versão '+ sliced; 
     };

	
	
	 $http.get('rest/layouts/projeto/'+$routeParams.gua_pro_id)
     .success(function(layouts ){
        $scope.layouts  = layouts ;
     })
     .error(function(error){
        console.log(erro);
     });
	
	 $scope.getApk = function(){
     $window.open(FileServiceApk.getFile('apkV'+ $scope.layout.gua_lay_num_ver  +'.doc'));
	 };
	 

	 $scope.getFile = function(){
	 $window.open(FileService.getFile('LayoutV' + $scope.layout.gua_lay_num_ver + '.doc'));
	 };
	
	
	  
	

}); 