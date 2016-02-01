angular.module('guaraniApp').controller('ValidadorController', ['$scope', 'Upload', '$timeout', '$http','$location', function ($scope, Upload, $timeout, $http, $location) {
  
	console.log('Load');
	$scope.tabelaserros;
    $scope.quantity = 20;
	$scope.filter = '';
	

	
	if(angular.isDefined($scope.user)){

	}else{
		console.log('Não autorizado!');
		$location.path('/login');
	}	
	 
	 
	$scope.getTotal = function(){
	    var total_sum = 0;
	    total_sum = ($scope.tabelaserros.telefone_mask_erros + $scope.tabelaserros.null_erros + $scope.tabelaserros.dados_abaixo
	    		+ $scope.tabelaserros.dados_acima + !isNaN($scope.tabelaserros.cnpj) + !isNaN($scope.tabelaserros.cpf));
	    $scope.total = total_sum;
	    console.log(total);
	    return total_sum;
	}; 


	$scope.uploadFiles = function(files, errFiles) { 
       
    	
    	$scope.files = files;
        $scope.errFiles = errFiles;
        
        console.log('Clicado'); 
        
        angular.forEach(files, function(file) {
            file.upload = Upload.upload({
                url: 'rest/relatorios/upload',
                data: {file: file}
            }); 

            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                    console.log(response.data);
                    	
                    
                    	$http.get('rest/relatorios')
	            		.success(function(tabelaserros){
	            			$scope.tabelaserros = tabelaserros;
	            			console.log(tabelaserros);
	                    	$scope.getTotal();

	            		}).error(function(){
	            			$scope.mensagem = 'Erro ao obter Tabelas de Erros!';
	            		});
                    	

            	
                });
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function (evt) {
                file.progress = Math.min(100, parseInt(100.0 * 
                                         evt.loaded / evt.total));
            });
        });
    }
	
	
	/*$scope.getTotal = function(){
    var total_sum = 0;
    total_sum = (!isNaN($scope.tabelaserros.telefone_mask_erros) + !isNaN($scope.tabelaserros.null_erros) + !isNaN($scope.tabelaserros.dados_abaixo)
    		+ !isNaN($scope.tabelaserros.dados_acima) + !isNaN($scope.tabelaserros.cnpj) + !isNaN($scope.tabelaserros.cpf) + !isNaN($scope.tabelaserros.cep)
    		+ !isNaN($scope.tabelaserros.frete) + !isNaN($scope.tabelaserros.sina) + !isNaN($scope.tabelaserros.estado_virgula) + !isNaN($scope.tabelaserros.tipo_pessoa)
    		+ !isNaN($scope.tabelaserros.limite_credito) + !isNaN($scope.tabelaserros.tipo_comissao) + !isNaN($scope.tabelaserros.politica_precos) 
    		+ !isNaN($scope.tabelaserros.prazo_min) + !isNaN($scope.tabelaserros.tipo_prod_cli) + !isNaN($scope.tabelaserros.codigo_virgula));
    $scope.total = total_sum;
    console.log(total);
    return total_sum;
	};*/

	
		
	
}]);