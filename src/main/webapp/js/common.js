function main() {
    $("#menu").load("menu.html");
    $("#footer").load("footer.html");
    route();
    $(window).bind('hashchange', function () {
        route();
    });
}

host = "http://" + location.hostname + ":" + 8080;

function route() {
    var url = document.URL;
    if (url.indexOf('#!') > 0) {
        var path;
        if (url.indexOf('?') > 0)
            path = url.substring(url.indexOf('#!') + 2, url.indexOf('?'));
        else path = url.substring(url.indexOf('!') + 1);
        loadToContent(path);
    } else loadToContent('home')
}
function loadToContent(page) {
    try {
        var content = $("#content");
        content.empty();
        unload();
        unload = function () {
        };
        content.load(page + ".html");
    } catch (err) {
        console.log(err);
    }

}

unload = function () {

}

function getParameter(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function drawContent(phones, tpl, to) {
    $('#' + tpl).tmpl(phones).appendTo('#' + to);
}

function getJson(url, callback) {
    $.ajax({
        url: url,
        type: 'GET',
        success: callback
    });
}
Controller = {
    get: function (url, callback) {
        return $.ajax({
            url: host + url,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            success: callback || null
        });
    },
    post: function (url, data, callback) {
        $.ajax({
            url: host + url,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: callback
        });
    },
    put: function (url, data, callback) {
        $.ajax({
            url: host + url,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: callback
        });
    },
    del: function (url, data, callback) {
        $.ajax({
            url: host + url,
            type: 'DELETE',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: callback
        });
    }
}

function openTab(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    try {
        evt.currentTarget.className += " active";
    } catch (e) {
        console.debug(e);
    }
}

function getFormattedDate(date) {
    var year = date.getFullYear();
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return year + '-' + month + '-' + day;
}
function drawChart(labels, data, label, ctx) {
    Chart.defaults.global.maintainAspectRatio = false;
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: label,
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}
