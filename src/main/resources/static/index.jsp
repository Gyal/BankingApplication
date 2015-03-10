<!DOCTYPE HTML>
<html ng-app ="BankingApp">
<head>
    <title>BankingAppl.ng</title>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet"/>
    <script src="js/libs/angular-resource.min.js"></script>
    <script src="js/app/application.js"></script>
    <script src="js/app/services.js"></script>
    <script src="js/app/application.js"></script>

</head>
<body>
    <div ng-view></div>
<div id ="BankingApplicationList">
    <h2> Liste de comptes</h2>

    <ul>
        <li ng-repeat="account dans comptes"></li>
        <a href="#/api/account/{{account.id}}">{{account.name}}</a>
    </ul>

    <button ng-clik="goAccountNewPage()">Ajoute</button>
</div>
</body>
</html>
</body>
</html>