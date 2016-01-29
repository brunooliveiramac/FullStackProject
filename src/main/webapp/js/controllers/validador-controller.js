angular.module('guaraniApp').controller('ValidadorController', ['$scope', 'Upload', '$timeout', '$http', function ($scope, Upload, $timeout, $http) {
  
	console.log('Load');
	$scope.tabelaserros;
    $scope.quantity = 20;
	$scope.filter = '';
	

	 
	$scope.getTotal = function(){
	    var total_sum = 0;
	    total_sum = ($scope.tabelaserros.telefone_mask_erros + $scope.tabelaserros.null_erros);
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
	
	
	
	
		
	
}]);