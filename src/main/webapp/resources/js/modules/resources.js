/**
 * Created by pc on 28.11.2015.
 */
angular.module("resource", ["ngResource"]).constant("baseUrl", "http://"+location.host)
.factory('Person', function(baseUrl, $resource){
    return getAsResource(baseUrl, "people", $resource);
});
function getAsResource(baseUrl, entityName, $resource){
    var paramObject = {id:'@id'};
    var crudUrl = baseUrl+"/model/"+entityName;
    var customMethods = {
        update: {url:crudUrl, method: 'PUT'},
        'insert':{url:crudUrl, method: 'POST'},
        'delete': {method: 'DELETE', params: {id: '@id'}},
    };
    var MainObject = $resource(crudUrl + '\/:id', paramObject, customMethods);
    return MainObject;
}