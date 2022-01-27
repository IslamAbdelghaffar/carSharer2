<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <!--   <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>HauptSeite</title>
</head>
<style>
    tr,
    td {
        border: 2px solid black;
    }

    table {
        border: 3px solid black;
        margin: 5px;

    }

    .table {
        display: table;
        border-collapse: separate;
        border-spacing: 50px;
        background-color: gray;

    }
    body{
        position:relative;
        left:700px;
    }


    .container {
        max-width: 500px;
        margin: auto;
        background: white;
        padding: 10px;


    }



    .button {
        display: flex;
        /* or display:grid */
        justify-content: center;
    }
</style>


<body style="width:500px;height:500px; align-content: center;">

<h1 style="font-family: courier; text-align: center; border-color: black; background-color: gray ;color:white;">
    CarSharer</h1>
<h2 style="font-family: courier;text-align: center; border-color: black;background-color:gray;color:white;">Meine
    reservierte Fahrten</h2>
<div class="container" style="text-align: center;"></div>
<form action="" onsubmit="submit()">
    <table align="center">
        <#list ReservFahrten as fahrts>

            <tr style="background-color: white;">

                <td style="width:400px;height:20px; text-align: center;">
                    <a href="FahrtDetails?fid=${fahrts.fid}&bid=${benutzer}" >Fahrt</a>
                    <button style="font-size:50px;color:black;"><i class="fa fa-envelope"></i></button>
                    <p><b>Von:</b> ${fahrts.startort}</p>
                    <p><b>Nach: </b>${fahrts.zielort}</p>
                    <p><b>status: </b>${fahrts.status}</p>
                </td>

            </tr>

        </#list>

    </table>
    <div>
        <h2
                style="font-family: courier;text-align: center;border-color: black; background-color: gray ;color:white;">
            offene fahrten</h2>
    </div>
    <table>

        <#list offeneFahrten as Offenfahrt>
            <tr style="background-color: white;">

                <td style="width:400px;height:20px;text-align: center;">
                    <a href="FahrtDetails?fid=${Offenfahrt.fid}&bid=${benutzer}" >Fahrt</a>
                    <button style="font-size:50px;color:black;"><i class="fa fa-envelope"></i></button>
                    <p><b>Von: ${Offenfahrt.startort }</b></p>
                    <p><b>Nach: ${Offenfahrt.zielort}</b></p>
                    <p><b>Freie Plätze: ${Offenfahrt.freierPlaetze}</b></p>
                    <p><b>fahrtkosten: ${Offenfahrt.fahrtkosten} &euro;</b></p>

                </td>
            </tr>
        </#list>


</form>
</table>
<form class="button" action="FahrtErstellen" style=" onsubmit=" submit()">
<input type="submit" value="fahrt"
       style="text-align:center; background-color:black; border-color: white;color:whitesmoke; width:100px;height:30px;">
</form>

</div>
</body>


</html>