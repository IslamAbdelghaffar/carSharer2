<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/main.css">
    <title>Fahrt Details</title>
</head>

<body>

<h2 style="text-align: center;"><b>Informationen</b></h2>

<div class="container2" style="text-align: center;">

    <p class="an"><b>Anbieter: </b>&ensp; ${benutzer.email !'keine email'}</p>
    <p class="fu"><b>fahrtdatum und -uhrzeit:</b> &ensp; ${FahrtDetails.fahrtdatumzeit}</p>
    <p class="vo"> <b>von: </b>&ensp; ${FahrtDetails.startort ! 'keine'}</p>
    <p class="na"><b>Nach: </b>&ensp; ${FahrtDetails.zielort ! 'keine'}</p>
    <p class="AnF"><b>Anzahl Freier-Plätze:</b> &ensp; ${FahrtDetails.freierPlaetze ! 'keine'}</p>
    <p class="FK"><b>Fahrtkosten: </b> &ensp; ${FahrtDetails.fahrtkosten ! 'keine'} &#8364;</p>
    <p class="ST"><b>Status: </b> &ensp; ${FahrtDetails.status}</p>
    <p class="besch"><b>Beschreibung: </b> </p>
    <textarea readonly class="txtx" id="Beschreibung" name="Beschreibung" rows="5"
              cols="40">${FahrtDetails.beschreibung ! 'keine beschreibung'} </textarea>
    <hr>
    <h2 class="AKT"><b>Aktionsliste</b></h2>
    <!-------------------------------Reservieren--------------------------------------------------------->

    <form action="FahrtDetails?action=reservieren" method="post" onsubmit="submit">

        <span><label for="kapazität"> Anzahl Plätze für Reservierung: &ensp;</label></span>
        <span><input type="number" id="quantity" name="Anzahl" min="1" max="2" value="1"
                     style="text-align:center;"></span>&ensp;&ensp;


        <input type="submit" value="Fahrt reservieren "
               style="Background-color:black;color:white;border-color: white;">
    </form>

    <!-------------------------------löschen--------------------------------------------------------->
    <form action="FahrtDetails?action=loeschen" method="post" onsubmit="submit()">
        <div class="mo"><input type="submit" value="Fahrt löschen"
                               style="Background-color:red;color:white;border-color: white; width:120px;">
        </div>
        <p id="x"> </p>
        <script>
            function submit(){
                document.getElementById("x").innerHTML="erfolgreiche reservirung :)"

            }
        </script>

    </form>
    <br>${message ! ''}<br>

    <hr>

    <span class="BEW"><b>Bewertungen</b></span>&emsp;&emsp;
    <span>Durchschnittsrating:&ensp; ${average ! 'keine' } </span><br><br>

    <table align="center">
        <tr>
            <th> &ensp; Benutzer &ensp;</th>
            <th> &ensp; Beschreibung&ensp;</th>
            <th> &ensp; Rating &ensp;</th>
        </tr>


        <#list benutzerBewertungEmail as be>
            <tr>
                <td>
                    &ensp;  ${be.email ! 'keine'}&ensp;
                </td>
            </tr>
        </#list>

        <#list bewertung as bewert>
            <tr>
                <td>
                    &ensp;  ${bewert.textnachricht ! 'keine'}
                </td>
            </tr>
        </#list>

        <#list bewertung as bewert>
            <tr>
                <td>
                    &ensp;  ${bewert.rating ! 'keine'}
                </td>
            </tr>
        </#list>




    </table><br>


    <a href="FahrtBewerten?bid=${user}&fid=${FahrtDetails.fid}"
       style="background-color:black;Border-color:white;color:White; height:25px; width:100px; padding:5px 5px; text-decoration: none;"><b>Fahrt
            bewerten</b></a><br><br>
</div>

</body>

</html>