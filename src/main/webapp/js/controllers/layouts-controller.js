angular.module('guaraniApp').controller('LayoutsController', function($scope, $http) {
   
	$scope.tree_data = [];

	
	$scope.layouts = [];
	
	$http.get('rest/layouts')
	.success(function(layouts){
		$scope.layouts = layouts;
	}).error(function(){
		$scope.mensagem = 'Erro ao obter Versões!';
	});
	
	
	$scope.update_tables = function(){
		console.log($scope.layout);
		console.log('Clicado!');
		tabelasPorVersao($scope.layout);
	}; 
	
	function tabelasPorVersao(id) {
		  $http.get('rest/tabelas/'+id)
	         .success(function(tree_data ){
	            $scope.tree_data  = tree_data ;
	    		console.log(tree_data);
	         })
	         .error(function(error){
	            console.log(erro);
	      });
	};
	
});