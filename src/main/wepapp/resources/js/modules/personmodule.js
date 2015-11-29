/**
 * Created by pc on 28.11.2015.
 */
angular.module('personmodule', ['resource']).controller('indexcontroller',function($scope, Person){
    $scope.people = Person.query();
    $scope.mode = 0;
    $scope.errorList = [];
    $scope.save = function(){
      $scope.person.$save().then(function(response){
          $scope.errorList = response.errorMessageList;
          $scope.person = new Person(response.entity);
          $scope.people = Person.query();
          if($scope.errorList && $scope.errorList.length == 0) {
              $('#personmodal').modal('hide');
              $scope.message = "Your record has been saved.";
              $('#messagebox').modal('show');
          }
      });
    };
    $scope.showdeleteConfirm = function(person){
        $scope.person = person;
        $('#deleteconfirmation').modal('show');
    };
    $scope.update = function(){
        $scope.person.$update().then(function(response){
            $scope.errorList = response.errorMessageList;
            $scope.person = new Person(response.entity);
            $scope.people = Person.query();
            if($scope.errorList && $scope.errorList.length == 0){
                $scope.message="Your record has been updated.";
                $('#messagebox').modal('show');
            }
        });
    };
    $scope.delete = function(){
        $scope.person.$delete().then(function(person){
            $scope.people = Person.query();
            if($scope.errorList && $scope.errorList.length == 0){
                $scope.message="Your record has been deleted.";
                $('#messagebox').modal('show');
            }
        });
        $('#deleteconfirmation').modal('hide');
    };
    $scope.edit = function(person){
       $scope.errorList =[];
      $scope.person = person;
      $scope.mode = 1;
      $('#personmodal').modal('show');
    };
    $scope.addNew = function(){
        $scope.errorList =[];
        $scope.mode = 0;
        $scope.person = new Person();
        $('#personmodal').modal('show');
    };
});

angular.module('personmodule').filter('tel', function () {
    return function (tel) {
        if (!tel) { return ''; }
        var length = tel.length;
        var retVal = tel;
        if(length > 3){
            retVal = tel.substring(0, 3);
            retVal += ' '+tel.substring(3);
        }
        if(length > 6){
            retVal = retVal.substring(0, 7);
            retVal += ' '+tel.substring(6);
        }
        if(length > 8){
            retVal = retVal.substring(0, 10);
            retVal += ' '+tel.substring(8);
        }
        return retVal;
    };
});

