<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/main.css">
    <title>Bonus</title>
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

    table {
        display: table;
        border-collapse: separate;
        background-color: rgb(224, 222, 222);
        border-spacing: 50px;
    }

    body {
        background-color: white;
        border: 3px solid black;
        max-width: 500px;
        height: auto;
        position: relative;
        left: 600px;
    }
</style>



<body style="background-color: lightgoldenrodyellow; margin-top: 70px;">


<div style="background-color: rgb(224, 222, 222); padding-top: 20px; text-align: center;">
    <form action='HauptSeite.htm' onsubmit="submit()">
        <input value="Home" type="submit"
               style="background-color: black;border-color: white;color:white;text-align: center; width:100px;height:20px;margin-bottom: 20px;">
    </form>
    <h1 style="text-align: center;"><b>Offene Fahrten des "besten Fahrers"</b></h1>

    <div style="text-align: center;">

        <p style="text-align: center;">fahrer: </p>
        <p style="text-align: center;">Durchschnitterating: </p>
        <form action="fahrt Details.htm" onsubmit="submit()">
            <table>
                <tr style="background-color: white;">
                    <#list fahrts as f>
                        <td style="width:400px;height:20px; text-align: center;">
                            <button style=" font-size:50px;color:black;"><i class="fa fa-envelope"></i></button>
                            <p><b>Fahrt ID:</b> ${f.fid  ! ''} </p>
                            <p><b>Von: </b>  ${f.startort !''}</p>
                            <p><b>Nach: </b> ${f.zielort  !''}</p>
                        </td>
                    </#list>
                </tr>
                <tr style="background-color: white;">

                </tr>
            </table>
        </form>
    </div>
</div>
</body>

</html>