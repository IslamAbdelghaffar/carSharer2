
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="functions.js"></script>
</head>
<style>
    tr,
    td {
        border: 2px solid black;
    }

    table {
        border: 3px solid #000000;
        margin: 5px;
    }

    table {
        display: table;
        border-collapse: separate;
        border-spacing: 50px;


    }


    .container {
        max-width: 500px;
        margin: auto;
        background-color: rgb(red, green, blue);
        padding: 10px;
    }
</style>

<body style="background-color: white;">
<form action='HauptSeite' onsubmit="submit()" style="text-align: center">
    <input value="Home" type="submit"
           style="background-color: black;border-color: white;color:white;text-align: center; width:100px;height:20px;margin-bottom:0px;margin-left:10px;">
</form>
<div class="container" style="border:1px solid black;width:40%; height:auto;background-color: white;margin-top: 15px;">
    <form action="FahrtSuche" method="post" target="_self" style="text-align:center;"><br><br>
        <label required for="start"><b>Start: </b></label>
        <input type="text" id="start" name="start" value="" onchange="start1()"
               style="text-align:center;">&ensp;
        <label required for="ziel"><b>Ziel: </b></label>
        <input type="text" id="ziel" name="ziel" value="" onchange="ziel1()"
               style="text-align:center;"><br><br>
        <div class="d"><label required for="ab"><b>ab: </b></label>
            <input type="date" id="ab" name="ab" value="01.01.2022">
        </div>
        <script>
            var date = new Date();
            var tdate = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getUTCFullYear();
            if (tdate < 10) {
                rdate = "0" + tdate;
            }
            if (month < 10) {
                month = "0" + month;
            }
            var mindate = year + "-" + month + "-" + tdate;
            document.getElementById("ab").setAttribute('min', mindate);
            console.log(mindate);
        </script>
        <form action="...." onsubmit="link()">
            <input class="a" type="submit" value="Suchen" onclick="myResults()"
                   style="Background-Color:black;Border-color:white;color:White;text-align:center;width:70px;height:30px;"><br><br>
        </form>
        <hr>


        <div id="Ergebnis">
            <h3 style="text-align: center;"><b>Suchergebnisse</b> </h3>


            <div class="container" style="text-align: center;"></div>
            <br>${message ! ''}</br>
            <table style="background-color: gray">

                <#list fahrten as f  >
                    <tr style="background-color: white;">
                        <td style="width:400px;height:20px;text-align: center;">
                            <a href='FahrtDetails'><img src="http://localhost:9109/icons/${f.transportmittel}.png"
                                                             style="width:60px; height:30px;border:1px solid black; margin-top: 10px;" alt="${f.transportmittel}.png"></a>
                            <br>Von: ${f.startort ! 'keine'}<br>
                            <br>Nach: ${f.zielort ! 'keine'}<br>
                            <br>Kosten: ${f.fahrtkosten ! 'keine'}<br>

                        </td>
                    </tr>

                </#list>
            </table>
        </div>


</div>

</div>

</body>

</html>