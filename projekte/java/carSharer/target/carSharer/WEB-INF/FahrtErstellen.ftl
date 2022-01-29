<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/main.css">
    <script src="../../../../../../../../../DB/Front%20end/functions.js"></script>
</head>

<body style="text-align: center; text-align-last:center;position:relative;top:50px;  ">


<h2 style="font-family:courier;border:1px solid black;width:40%; background-color:gray;margin-left: 50%; margin-bottom:5px;position:relative;top:10px ">Fahrt erstellen </h2>

<div class="container2" style="border:1px solid black;background-color: gray; width:40%;margin-left: 50%;margin-top: 20px;">
    <form action="FahrtErstellen?action=FahrtErstellen" target="_self" method="post" onsubmit="submit()">
        <label for="von"> <b><br>Von:</b></label>
        <input type="text" id="von" name="von" value="Duisburg" style="text-align:center;"> <br><br>
        <label for="nach"> <b>Bis:</b> </label>
        <input type="text" id="nach" name="nach" value="München" style="text-align:center;"><br><br>
        <div class="x"><label for="kapazität"> <b>Maximale kapazität:</b></label></div>
        <div class="z"><input type="number" id="kapazität" name="kapazität" min="1" max="10" value="5" style="text-align:center;"></div>
        <br><br>
        <div class="y"><label for="kosten"> <b>Fahrtkosten:</b> </label></div>
        <div class="f"><input type="number" id="kosten" name="kosten" min="0" value="50" style="text-align:center;">
            &euro; </div><br><br>

        <div class="e"> <label for="Transportmittel"> <b>Transportmittel:</b> </label></div>
        <input type="radio" id="1" name="Transportmittel" value="1" required>
        <label for="Auto">Auto</label>
        <input type="radio" id="2" name="Transportmittel" value="2" required>
        <label for="bus">Bus</label>
        <input type="radio" id="3" name="Transportmittel" value="3" required>
        <label for="kleintransporter">kleintransporter</label><br><br>
        <div class="g"><label for="date"> <b>Fahrtdatum: </b></label></div>
        <div class="s"><input type="date" id="date" name="date" value="2022.02.02"></div>
        <label for="time"> &nbsp;</label>
        <input type="time" id="time" name="time" value="12.00" min="00.00" max="24.00"><br><br>
        <div class="w"><label for="Beschreibung"><b>Beschreibung:</b> </label></div><br>
        <textarea required class="q" id="Beschreibung" name="Beschreibung" rows="5" cols="30"
                  maxlength="50">Bin sehr gesprächig und mach wenige Pausen </textarea><br>
        <input class="o" type="submit" value="Erstellen" style="Background-Color:black
        ;Border-color:white;color:White;height:30px; ">
        <a class="HOM" href='HauptSeite'
           style="background-color:black;Border-color:white;color:White; height:15px; width:100px; padding:5px 5px; text-decoration: none;"><b>Home</b></a><br><br>

    </form>

    ${message ! ''}
</div>
</bod>

</html>