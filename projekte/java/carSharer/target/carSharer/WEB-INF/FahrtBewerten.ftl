<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
<h2 style="font-family:courier;text-align:center;">Fahrt Bewertung</h2>
<div class="container2">
    <form class="bo" action="FahrtBewerten?action=bewert" target="_self" style="text-align:center;" method="post">
        <label for="Bewertungstext"><b><br>Bewertungstext:</b></label><br><br>
        <textarea id="Beschreibung" name="Beschreibung" rows="5" cols="50"
                  style=text-align:left;> Die Fahrt war super! </textarea><br>
        <label for="Bewertungsrating"><b>Bewertungsrating:</b></label><br><br>
        <input type="radio" id="1" name="Bewertungsrating" value="1">
        <label for="1">1</label>
        <input type="radio" id="2" name="Bewertungsrating" value="2">
        <label for="2">2</label>
        <input type="radio" id="3" name="Bewertungsrating" value="3">
        <label for="3">3</label><br><br>
        <input type="radio" id="4" name="Bewertungsrating" value="4">
        <label for="4">4</label>
        <input type="radio" id="5" name="Bewertungsrating" value="5" checked>
        <label for="5">5</label><br><br>
        <input type="submit" value="Bewerten"
               style="Background-Color:blue;Border-color:Blue;color:White;text-align:center;">

    </form>
    <br><br>
    <div style="text-align: center">${message ! ''}</div>

</div>

</body>

</html>