const toggleBtn = document.querySelector('#toggleBtn');
const divList = document.querySelector('.listHolder');
const oddButtons = document.querySelectorAll('.odd-button');
const random = Math.floor(Math.random() * 100000);




oddButtons.forEach(button =>{
    button.addEventListener('click',() => {
        const ul = divList.querySelector('ul');
        const li = document.createElement('li');

        if(button.style.borderColor === 'rgb(238, 119, 66)'){
            // não selecionado
            button.style.borderColor = '#16350e';
        } else {
            // selecionado
            button.style.borderColor = '#ee7742';
        }
        if(button.style.backgroundColor === 'rgb(238, 119, 66)'){
            // não selecionado
            button.style.backgroundColor = '#ffffff';
            const child = document.querySelector('#' +  button.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#choosenTeam').value.replace(/[^A-Z0-9]+/ig, "_"));
            ul.removeChild(child);
        } else {
            // selecionado
            button.style.backgroundColor = '#ee7742';

            const oddTotal = document.querySelector(".oddTotal").textContent;
            const odd = parseFloat(button.innerHTML.split('<br>')[1].match("<span>(.*)<\\/span>")[1]);
            document.querySelector(".oddTotal").innerHTML = (parseFloat(oddTotal) * odd).toString();

            const numberOfBets = ul.getElementsByTagName('li').length;
            var multipleId = "";
            if(numberOfBets === 1){
                multipleId = '<input type="hidden" id="multipleID" name="0">'
            } else {
                multipleId = '<input type="hidden" id="multipleID" name="' + random + '">'
            }

            li.innerHTML = '<div class="aposta mb-1 px-4">'
                + '<b>' + button.querySelector('#homeTeam').value + '-' + button.querySelector('#awayTeam').value + '</b>'
                + '</div> <div class="aposta px-4 mb-4">'
                + '<br> Resultado Aposta: <b> ' +  button.innerHTML.split('<br>')[0] + '</b>'
                + '<div class="container"> Odd: <b>' +  button.innerHTML.split('<br>')[1]
                +'</b></div>' + '<input type="hidden" name="gameId" value="' + button.querySelector('#gameId').value + '">'
                + '<input type="hidden" name="bettedTeam" value="' + button.querySelector('#choosenTeam').value + '">'
                + '<input type="hidden" name="value" value="' + document.querySelector('#bettedValue') + '">'
                + '<input type="hidden" name="possibleGain" value="' + parseFloat(document.querySelector('#bettedValue')) * parseFloat(oddTotal) * odd + '">'
                +   multipleId +
                + '</div>';
            li.id = button.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#choosenTeam').value.replace(/[^A-Z0-9]+/ig, "_");
            console.log(li.id);
            ul.appendChild(li);

            console.log('gameID: ' + button.querySelector('#gameId').value);
            console.log('choosen Team: ' + button.querySelector('#choosenTeam').value);
            console.log('bettedValue: ' + document.querySelector('#bettedValue'));
            console.log('possibleGain: ' + parseFloat(document.querySelector('#bettedValue')) * parseFloat(oddTotal) * odd);
            console.log('multipleID: ' + random);


        }

    });

});

const valorApostado = document.querySelector('#bettedValue');

valorApostado.addEventListener('input',() => {
    const oddTotal =  document.querySelector(".oddTotal").textContent;
    const bettedValue = document.querySelector('#bettedValue').value;
    document.querySelector('.possibleGains').innerHTML = (parseFloat(oddTotal) * parseFloat(bettedValue)).toString();
});