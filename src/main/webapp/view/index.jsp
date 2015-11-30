<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 28.11.2015
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="personmodule">
<head>
    <title>Personel</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/resources/js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/js/angular.js"></script>
    <script type="text/javascript" src="/resources/js/angular-resource.js"></script>
    <script type="text/javascript" src="/resources/js/modules/resources.js"></script>
    <script type="text/javascript" src="/resources/js/modules/personmodule.js"></script>
</head>
<body ng-controller="indexcontroller">
<div class="container-fluid">
    <div class="col-md-3 col-lg-3"></div>
    <div class="col-md-6 col-lg-6">
        <div class="panel">
            <table class="table table-bordered table-striped">
                <caption>Person List</caption>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Address</th>
                        <th>Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                <tr ng-repeat="person in people">
                    <td>{{person.id}}</td>
                    <td>{{person.name}}</td>
                    <td>{{person.surname}}</td>
                    <td>{{person.address}}</td>
                    <td>{{person.phoneNumber | tel}}</td>
                    <td><button class="btn btn-warning" ng-click="showdeleteConfirm(person)">Delete</button></td>
                    <td><button class="btn btn-success" ng-click="edit(person)">Edit</button></td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td cellspan="5"><button class="btn btn-primary" ng-click="addNew()">Add</button></td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
    <div class="col-md-3 col-lg-3"></div>

    <div id="messagebox" class="modal fade" role="dialog" style="z-index: 1050">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Message</h4>
                </div>
                <div class="modal-body">
                    <span>{{message}}</span>
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
                </div>
            </div>

        </div>
    </div>

    <div id="deleteconfirmation" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Confirm</h4>
                </div>
                <div class="modal-body">
                    <span><span style="font-weight: bold">"Name:{{person.name}} Surname:{{person.surname}}"</span> This record will be deleted, do u want to contiune ?</span>
                </div>
                <div class="modal-footer">
                    <button ng-click="delete()" class="btn btn-success">Yes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                </div>
            </div>

        </div>
    </div>

    <div id="personmodal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Confirmation</h4>
                </div>
                <div class="modal-body">
                    <div>
                        <ul>
                          <li ng-repeat="error in errorList">
                              {{error.field}} {{error.defaultMessage}}
                          </li>
                        </ul>
                    </div>
                    <form role="form" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-md-3">Id</label>

                            <div class="col-md-9">
                                <input type="text" class="form-control" ng-model="person.id"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Name</label>

                            <div class="col-md-9">
                                <input type="text" class="form-control" ng-model="person.name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Surname</label>

                            <div class="col-md-9">
                                <input type="text" class="form-control"  ng-model="person.surname"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Address</label>

                            <div class="col-md-9">
                                <input type="text" class="form-control"  ng-model="person.address"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Phone Number</label>

                            <div class="col-md-9">
                                <input type="text" class="form-control"  ng-model="person.phoneNumber"/>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button ng-click="update()" class="btn btn-success" ng-show="mode==1">Update</button>
                    <button ng-click="save()" class="btn btn-success" ng-show="mode==0">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
