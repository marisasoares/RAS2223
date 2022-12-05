const gamesIds = [];
const divList = document.querySelector('.listHolder');
const ul = divList.querySelector('ul');
const oddsInput = document.querySelectorAll('.odd');

oddsInput.forEach(input => {
    input.addEventListener('input',() => {
        const li = document.createElement('li');
        let gameId = input.parentElement.querySelector("#gameId").value + input.parentElement.querySelector("#choosenTeam").value;
        if(gamesIds.includes(gameId)) {
            const child = document.querySelector('#' +  input.parentElement.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + input.parentElement.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + input.parentElement.querySelector('#choosenTeam').value);
            ul.removeChild(child);
            li.innerHTML = '<div class="aposta mb-1 px-4">'
                + '<b>' + input.parentElement.querySelector('#homeTeam').value + '-' + input.parentElement.querySelector('#awayTeam').value + '</b>'
                + '</div> <div class="aposta px-4 mb-4">'
                + '<br> Alterada Odd: <b> ' +  input.parentElement.textContent + '</b>'
                + '<div class="container"> Odd: <b>' + parseFloat(input.value) + '</b></div>'
                + '<input type="hidden" name="gameId" value="' + input.parentElement.querySelector('#gameId').value + '">'
                + '</div>';
            li.id = input.parentElement.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + input.parentElement.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + input.parentElement.querySelector('#choosenTeam').value;
            ul.appendChild(li);
            return;
        }
        document.querySelector(".oddChanged").innerHTML = (gamesIds.length + 1).toString();
        gamesIds.push(gameId);
        console.log('gameId: ' + input.parentElement.querySelector("#gameId").value);
        li.innerHTML = '<div class="aposta mb-1 px-4">'
            + '<b>' + input.parentElement.querySelector('#homeTeam').value + '-' + input.parentElement.querySelector('#awayTeam').value + '</b>'
            + '</div> <div class="aposta px-4 mb-4">'
            + '<br> Alterada Odd: <b> ' +  input.parentElement.textContent + '</b>'
            + '<div class="container"> Odd: <b>' + parseFloat(input.value) + '</b></div>'
            + '<input type="hidden" name="gameId" value="' + input.parentElement.querySelector('#gameId').value + '">'
            + '</div>';
        li.id = input.parentElement.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + input.parentElement.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + input.parentElement.querySelector('#choosenTeam').value;
        ul.appendChild(li);
    });
});