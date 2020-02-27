package Config.DataBase;

public class TestResultsHTMLCode {


    public static String tcHTMLCode = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title> SAF - SURESH CHIRRA </title> \n" +
            "<style>\n" +
            "\n" +
            "#tcTable {  \n" +
            "  border: 1px solid blue;\n" +
            "   border-collapse: collapse;\n" +
            "  padding: 15px;\n" +
            "   width: 100%;\n" +
            "   table-layout:fixed;\n" +
            "   word-wrap:break-word;\n" +
            " }\n" +
            "#tcTableTh{\n" +
            "\tborder: 1px solid powderblue;\n" +
            "  padding: 10px;\n" +
            "  width : 160%;\n" +
            "  text-align: left;\n" +
            "  color: blue;\n" +
            "}\n" +
            "#headerText{\n" +
            "border: 1px solid powderblue;\n" +
            " text-align: center;\n" +
            " padding: 16px;\n" +
            " color: blue;\n" +
            "}\n" +
            "#tcTableTd {\n" +
            "\tborder: 1px solid powderblue;\n" +
            "  padding: 10px;\n" +
            "}\n" +
            "\n" +
            "</style>\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<h2 id='headerText'>SURESH CHIRRA &nbsp &nbsp<i> SAF </i>&nbsp &nbsp TEST RESULTS</h2>\n" +
            "<h2 id='headerText'>Test Case info : ###TCNAME###</h2>\n" +
            "\n" +
            "<table id='tcTable'>\n" +
            "  <tr>\n" +
            "    <th id='tcTableTh'>Step Sno</th>\n" +
            "    <th id='tcTableTh'>Step Name</th>\n" +
            "\t<th id='tcTableTh'>Step Details</th>\n" +
            "\t<th id='tcTableTh'>Status</th>\n" +
            "\t<th id='tcTableTh'>Date & Time info</th>\n" +
            "\t<th id='tcTableTh'>Image Path</th>\n" +
            "  </tr>\n" +
            "  \n" +
            "  ###TCDETAILS###\n" +
            "  </tr>\n" +
            "  \n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>";


    public static String summaryHTMLCode = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title> SAF - SURESH CHIRRA\n" +
            "    </title> \n" +
            "<style>\n" +
            "#mainTable {  \n" +
            "  border: 1px solid blue;\n" +
            "  padding: 15px;\n" +
            "  border-collapse: collapse;\n" +
            "  width: 100%;\n" +
            "  table-layout:fixed;\n" +
            "}\n" +
            "\n" +
            "#tcTable {  \n" +
            "  border: 1px solid blue;\n" +
            "   border-collapse: collapse;\n" +
            "  padding: 15px;\n" +
            "   width: 100%;\n" +
            "   table-layout:fixed;\n" +
            "   word-wrap:break-word;\n" +
            " }\n" +
            "\n" +
            "#mainTableTh{\n" +
            "\tborder: 1px solid powderblue;\n" +
            "  padding: 10px;\n" +
            "  width : 33%;\n" +
            "  text-align: center;\n" +
            "  color: blue;\n" +
            "}\n" +
            "\n" +
            "#exeInfo{\n" +
            " padding: 10px;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "#tcTableTh{\n" +
            "\tborder: 1px solid powderblue;\n" +
            "  padding: 10px;\n" +
            "  width : 11%;\n" +
            "  text-align: left;\n" +
            "  color: blue;\n" +
            "}\n" +
            "\n" +
            "#mainTableTd {\n" +
            "\tborder: 1px solid powderblue;\n" +
            "  padding: 10px;\n" +
            "  width : 50%;\n" +
            "\n" +
            "}\n" +
            "\n" +
            "#blueColor{\n" +
            "\tcolor: blue;\n" +
            "}\n" +
            "\n" +
            "#headerText{\n" +
            "border: 1px solid powderblue;\n" +
            " text-align: center;\n" +
            " padding: 20px;\n" +
            " color: blue;\n" +
            "}\n" +
            "\n" +
            "#piechart, #piechart_2 {\n" +
            "  display: block;\n" +
            "  margin-left: auto;\n" +
            "  margin-right: auto;\n" +
            "}\n" +
            "\n" +
            "#tcTableTd {\n" +
            "\tborder: 1px solid powderblue;\n" +
            "  padding: 10px;\n" +
            "\n" +
            "\n" +
            "\n" +
            "}\n" +
            "\n" +
            "</style>\n" +
            "\n" +
            "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
            "   \n" +
            "\t<script type=\"text/javascript\">\n" +
            "      google.charts.load(\"current\", {packages:[\"corechart\"]});\n" +
            "      google.charts.setOnLoadCallback(drawChart);\n" +
            "      function drawChart() {\n" +
            "        var data = google.visualization.arrayToDataTable([\n" +
            "          ['Task', 'Hours per Day'],\n" +
            "          ['PASS',      ###TCPASSCOUNT###],\n" +
            "          ['FAIL', ###TCFAILCOUNT###]\n" +
            "        ]);\n" +
            "\n" +
            "        var options = {\n" +
            "          title: '',\n" +
            "          pieHole: 0.4,\n" +
            "        };\n" +
            "\n" +
            "        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));\n" +
            "        chart.draw(data, options);\n" +
            "      }\n" +
            "    </script>\n" +
            "\t\n" +
            "\t <script type=\"text/javascript\">\n" +
            "      google.charts.load('current', {'packages':['corechart']});\n" +
            "      google.charts.setOnLoadCallback(drawChart_2);\n" +
            "\n" +
            "      function drawChart_2() {\n" +
            "\n" +
            "        var data = google.visualization.arrayToDataTable([\n" +
            "          ['Task', 'Test Cases Status'],\n" +
            "          ['PASS',  ###STEPPASSCOUNT###],\n" +
            "          ['FAIL', ###STEPFAILCOUNT###]\n" +
            "        ]);\n" +
            "\n" +
            "        var chart = new google.visualization.PieChart(document.getElementById('piechart_2'));\n" +
            "\n" +
            "        chart.draw(data, '');\n" +
            "      }\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<h2 id='headerText'>SURESH CHIRRA &nbsp &nbsp<i> SAF </i>&nbsp &nbsp TEST RESULTS</h2>\n" +
            "\n" +
            "<table id='mainTable'>\n" +
            "  <tr>\n" +
            "    <th id='mainTableTh'>Test Cases Status</th>\n" +
            "    <th id='mainTableTh'>Test Step Status</th>\n" +
            "\t<th id='mainTableTh'>Execution Info</th>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td id='mainTableTd'><div id=\"donutchart\" style=\"width: 400px; height: 400px;\"></div></td>\n" +
            "    <td id='mainTableTd'><div id=\"piechart_2\" style=\"width: 400px; height: 400px;\"></div></td>\n" +
            "\t<td id='mainTableTd'>\n" +
            "\t\n" +
            "\t<p id='exeInfo'> OS : ###OSNAME###</p>\n" +
            "\t<p id='exeInfo'> UserId : ###UID###</p>\n" +
            "\t<p id='exeInfo'> Executed At : ###EXETIME###</p>\n" +
            "\t</td>\n" +
            "  </tr>\n" +
            "  \n" +
            "</table>\n" +
            "\n" +
            "\n" +
            "<h2 id='headerText'>Test cases info</h2>\n" +
            "\n" +
            "<table id='tcTable'>\n" +
            "  <tr>\n" +
            "    <th id='tcTableTh'>SNo</th>\n" +
            "\t<th id='tcTableTh'>Test Case Names</th>\n" +
            "\t<th id='tcTableTh'>Status</th>\n" +
            "\t<th id='tcTableTh'>No Of Steps Passed</th>\n" +
            "\t<th id='tcTableTh'>No Of Steps Failed</th>\n" +
            "\t<th id='tcTableTh'>Start Time</th>\n" +
            "\t<th id='tcTableTh'>End Time</th>\n" +
            "\t<th id='tcTableTh'>Duration</th>\n" +
            "\t\n" +
            "\t<th id='tcTableTh'>Details</th>\n" +
            "  </tr>\n" +
            " \n" +
            " \n" +
            " \n" +
            " ###TCDATA###\n" +
            "  \n" +
            "</table>\n" +
            "\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}
