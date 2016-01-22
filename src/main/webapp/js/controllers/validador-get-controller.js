angular.module('guaraniApp').controller('ValidadorGetController', function($scope, $http, $location, projetosResource, projetoRegistro, ParamService) {

	
	$scope.tabelaserros = [];
	
	$http.get('rest/relatorios')
	.success(function(tabelaserros){
		$scope.tabelaserros = tabelaserros;
	}).error(function(){
		$scope.mensagem = 'Erro ao obter Tabelas de Erros!';
	});
	
});