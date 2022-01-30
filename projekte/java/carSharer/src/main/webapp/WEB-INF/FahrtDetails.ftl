<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/main.css">
    <title>Fahrt Details</title>


</head>
<style>
    table,
    th,
    td {
        border: 1px solid black;

    }
    body{
        position:relative;
        left:700px;
    }

    .table {
        text-align: "center";
        width: 300px;
        padding-left: 50px;
        margin: 10px;

    }
</style>

<body style="background-color: white;margin-top: 70px;">

<form action='HauptSeite' onsubmit="submit()">
    <input class="control" value="Home" type="submit"
           style="background-color: black;border-color: white;color:white;text-align: center; width:100px;height:30px;margin-left:350px;margin-top:-15px ;">
</form>

<h2 style="text-align: center;width:40%;height:40px;border:1px solid black;background-color: rgb(224,222,222);padding-bottom: 0px;margin-bottom: 5px;"><b>Informationen</b></h2>


<div class="container2" style="text-align: center; border:1px solid black;align-items:center;background-color:rgb(224,222,222);width:40%;height:auto;margin-top: 10px;">

    <a href=''><img src="http://localhost:9109/icons/${FahrtDetails.transportmittel}.png"
                                                         style="width:60px; height:30px;border:1px solid black; margin-top: 10px;" alt="${FahrtDetails.transportmittel}.png"></a>
    <p class="an"><b>Anbieter: </b>&ensp;  ${benutzer.email !'keine email'} </p>
    <p class="fu"><b>fahrtdatum und -uhrzeit:</b> &ensp;${FahrtDetails.fahrtdatumzeit} </p>
    <p class="vo"> <b>von: </b>&ensp; ${FahrtDetails.startort ! 'keine'}</p>
    <p class="na"><b>Nach: </b>&ensp; ${FahrtDetails.zielort ! 'keine'}</p>
    <p class="AnF"><b>Anzahl Freier-Plätze:</b> &ensp; ${FahrtDetails.freierPlaetze ! 'keine'}</p>
    <p class="FK"><b>Fahrtkosten: </b> &ensp; ${FahrtDetails.fahrtkosten ! 'keine'} &#8364;</p>
    <p class="ST"><b>Status: </b> &ensp;  ${FahrtDetails.status}</p>
    <p class="besch"><b>Beschreibung: </b> </p>
    <textarea readonly class="txtx" id="Beschreibung" name="Beschreibung" rows="5"
              cols="40">${FahrtDetails.beschreibung ! 'keine beschreibung'} </textarea>
    <hr>
    <!-------------------------------Reservieren--------------------------------------------------------->

    <h2 class="AKT"><b>Aktionsliste</b></h2>
    <form onsubmit="required()" name="form1" action="FahrtDetails?action=reservieren" method="post">
        <span><label for="kapazität"> Anzahl Plätze für Reservierung: &ensp;</label></span>
        <span><input type="number" name="Anzahl" min="1" max="2" placeholder="no"
                     style="text-align:center;width:40px;" required></span>&ensp;&ensp;
        <input type="submit" name="submit" value="fahrt reservieren"
               style="Background-color:black;color:white;border-color: white;text-align: center;">
    </form>

    <!-------------------------------löschen--------------------------------------------------------->
    <form action="FahrtDetails?action=loeschen" method="post" obsubmit="submit()">
        <input type="submit" value="Fahrt Löschen"
               style="Background-color:red;color:white;border-color: white; width:110px;margin-left:290px; ">
        <p id="demo" style="text-align: center;"></p>
    </form>

    <br>${message ! ''}<br>



    <hr>


    <span class="BEW"><b>Bewertungen</b></span>&emsp;&emsp;&emsp;&emsp;&emsp;
    <span>Durchschnittsrating:&ensp; 4,33 </span><br><br>




    <table align="center">
        <thead>
        <tr>
            <th>Benutzer</th>
            <th>Beschreibung</th>
            <th>Rating</th>
        </tr>

        <#list Rates as R>

            <tr>
                <th>${R.benutzerEmail !''}</th>
                <th>${R.beschreibung ! ''}</th>
                <th>${R.rate !''}</th>
            </tr>
        </#list>
        </thead>

        <tbody id="tableData"></tbody>
    </table><br>
    <a href="FahrtBewerten?bid=${user}&fid=${FahrtDetails.fid}"
       style="background-color:black;Border-color:white;color:White; height:25px; width:100px; padding:5px 5px; text-decoration: none;"><b>Fahrt
            bewerten</b></a><br><br>
</div>



<pt>


</body>

</html>