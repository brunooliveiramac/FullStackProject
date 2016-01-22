angular.module('guaraniApp').controller('ProjetoController', function($scope, $routeParams, $location, projetoRegistro, projetosResource) {
	
	$scope.projeto = {};
	$scope.message = '';  
	

	if($routeParams.gua_pro_id) {
		projetosResource.get({username: $scope.user.name, gua_pro_id : $routeParams.gua_pro_id, }, function(projeto) {
			$scope.projeto = projeto;
			console.log(projeto);
		}, function(error) {
			console.log(error);
			$scope.message = 'Ocorreu um erro o obter o Projeto!'
		});
	}
	
	$scope.save = function() {
		projetoRegistro.register($scope.projeto)
		.then(function(data) {
			$scope.message = data.message; 
			if(data.include) $scope.projeto = {};
			$location.path('/');

		}) 
		.catch(function(data) {
			$scope.message = data.message;
		});
	}
});