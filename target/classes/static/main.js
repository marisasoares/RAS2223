const toggleBtn = document.querySelector('#toggleBtn');
const divList = document.querySelector('.listHolder');
const oddButtons = document.querySelectorAll('.odd-button');
const random = Math.floor(Math.random() * 100000);

var oddTotal = 1;
var possibleGain = 0;
var bettedValue = 0;

document.querySelector('#multipleId').value = random;

oddButtons.forEach(button =>{
    button.addEventListener('click',() => {
        const ul = divList.querySelector('ul');
        const li = document.createElement('li');

        // change border color
        button.style.borderColor = button.style.borderColor === 'rgb(238, 119, 66)' ? '#16350e' : '#ee7742';

        if(button.style.backgroundColor === 'rgb(238, 119, 66)'){
            // Button selected
            button.style.backgroundColor = '#ffffff';
            // Get element with id #homeTeam#awayTeam#choosenTeam
            const child = document.querySelector('#' +  button.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#choosenTeam').value);
            ul.removeChild(child);

            // Get odd
            const odd = parseFloat(button.querySelector("#odd").value);
            oddTotal = oddTotal / odd;
            document.querySelector(".oddTotal").innerHTML = oddTotal;
        } else {
            // Button not selected
            button.style.backgroundColor = '#ee7742';
            // Get odd
            const odd = parseFloat(button.querySelector("#odd").value);
            bettedValue = parseFloat(document.querySelector('#bettedValue').value)
            oddTotal = oddTotal * odd;
            possibleGain = bettedValue * oddTotal;
            document.querySelector(".oddTotal").innerHTML = oddTotal;
            document.querySelector(".possibleGain").innerHTML = possibleGain;
            document.querySelector('#possibleGain').value = possibleGain;
            document.querySelector('#oddTotal').value = oddTotal;

            const numberOfBets = ul.getElementsByTagName('li').length;

            li.innerHTML = '<div class="aposta mb-1 px-4">'
                + '<b>' + button.querySelector('#homeTeam').value + '-' + button.querySelector('#awayTeam').value + '</b>'
                + '</div> <div class="aposta px-4 mb-4">'
                + '<br> Resultado Aposta: <b> ' +  button.innerHTML.split('<br>')[0] + '</b>'
                + '<div class="container"> Odd: <b>' + odd + '</b></div>'
                + '<input type="hidden" name="gameId" value="' + button.querySelector('#gameId').value + '">'
                + '<input type="hidden" name="bettedTeam" value="' + button.querySelector('#choosenTeam').value + '">'
                + '</div>';
            li.id = button.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#choosenTeam').value;
            console.log(li.id);
            ul.appendChild(li);
        }

    });

});

const inputBetValue = document.querySelector('#bettedValue');

inputBetValue.addEventListener('input',() => {
    bettedValue = document.querySelector('#bettedValue').value;
    possibleGain = bettedValue * oddTotal;
    document.querySelector('.possibleGain').innerHTML = possibleGain;
    document.querySelector('#possibleGain').value = possibleGain;
    document.querySelector('#oddTotal').value = oddTotal;
});