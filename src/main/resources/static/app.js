function searchByPhone() {
    var phone = document.getElementById("search_field").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var client = JSON.parse(this.responseText);
            var html = '<tr>\n' +
                '        <th>Номер</th>\n' +
                '        <th>Имя</th>\n' +
                '        <th>Телефон</th>\n' +
                '        <th>Кол-во чашек</th>\n' +
                '        <th>Всего чашек</th>\n' +
                '        <th>Увеличить</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';
            html = html + '<tr><td>' + client.clientId + '</td>\n' +
                '        <td>' + client.name + '</td>\n' +
                '        <td>' + client.phone + '</td>\n' +
                '        <td>' + client.cupCount + '</td>' +
                '        <td>' + client.allCup + '</td>' +
                '        <td><button class="btn btn--table" onclick="incrClientCup(' + client.clientId + ')">Увеличить</button></td>' +
                '        <td><button class="btn btn--table btn--delete" onclick="deleteClient(' + client.clientId + ')">Удалить</button></td></tr>';
            document.getElementById("clientsList").innerHTML = html;
        }
    };
    xhttp.open("GET", "https://cubby-app.herokuapp.com/clients/findByPhone?phone=" + phone, false);
    xhttp.send();
}

function deleteClient(clientId) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "https://cubby-app.herokuapp.com/clients/delete/" + clientId, false);
    xhttp.send();

    loadClients();
}

function incrClientCup(clientId) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "https://cubby-app.herokuapp.com/clients/incr/" + clientId, false);
    xhttp.send();

    loadClients();
}

function createClient() {
    var clientName = document.getElementById("client_name").value;
    var clientPhone = document.getElementById("client_phone").value;
    var clientCupCount = document.getElementById("client_cupCount").value;

    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("POST", "https://cubby-app.herokuapp.com/clients/save", false);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({name: clientName, phone: clientPhone, cupCount: clientCupCount}));

    loadClients();
}

function loadClients() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var clients = JSON.parse(this.responseText);
            var html = '<tr>\n' +
                '        <th>Номер</th>\n' +
                '        <th>Имя</th>\n' +
                '        <th>Телефон</th>\n' +
                '        <th>Кол-во чашек</th>\n' +
                '        <th>Всего чашек</th>\n' +
                '        <th>Увеличить</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';
            for (var i = 0; i < clients.length; i++) {
                var client = clients[i];
                console.log(client);
                html = html + '<tr><td>' + client.clientId + '</td>\n' +
                    '        <td>' + client.name + '</td>\n' +
                    '        <td>' + client.phone + '</td>\n' +
                    '        <td>' + client.cupCount + '</td>' +
                    '        <td>' + client.allCup + '</td>' +
                    '        <td><button class="btn btn--table" onclick="incrClientCup(' + client.clientId + ')">Увеличить</button></td>' +
                    '        <td><button class="btn btn--table btn--delete" onclick="deleteClient(' + client.clientId + ')">Удалить</button></td></tr>';
            }
            document.getElementById("clientsList").innerHTML = html;
        }
    };
    xhttp.open("GET", "https://cubby-app.herokuapp.com/clients/findAll", false);
    xhttp.send();
}

loadClients();