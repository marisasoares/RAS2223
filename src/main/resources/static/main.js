const betBtn = document.querySelector('#betBtn');
const divList = document.querySelector('.listHolder');
const oddButtons = document.querySelectorAll('.odd-button');
const betTypeBtn = document.querySelectorAll('.betTypeBtn');
const random = Math.floor(Math.random() * 100000);

// Array with the current games selected
const gamesIds = [];
const odds = [];
var oddTotal = 0;
var possibleGain = 0;
var bettedValue = 0;
var currency = "euros";
var euros = parseFloat(document.querySelector("#euros").textContent.slice(0, -1));
var dollars = parseFloat(document.querySelector("#dollars").textContent.slice(0, -1));
var betType = document.querySelector('input[name="betType"]:checked').value;

switch (currency){
    case "euros":
        document.querySelector(".possibleGain").innerHTML = 0 + " €";
        break;
    default:
        document.querySelector(".possibleGain").innerHTML = 0 + " $";
        break;
}

const radioBtn = document.getElementsByName("betType");

radioBtn.forEach(radioBtn =>{
    radioBtn.addEventListener('change',()=>{
       betType =  document.querySelector('input[name="betType"]:checked').value;
       if(hasDuplicates(gamesIds) && betType === "multipla") {
           alert("Existem jogos em que foram selecionados resultados diferentes para o mesmo jogo, o que invalida apostas multiplas. Por favor selecione apenas um resultado por jogo");
            document.getElementById("simples").checked = true;
       }
        oddTotal = calculateOddTotal(odds);
        document.querySelector(".oddTotal").innerHTML = oddTotal + (betType === 'simples' ? ' ( soma das odds)' : ' ( produto das odds)');

    });
});



document.querySelector('#multipleId').value = random;

betTypeBtn.forEach(button =>{
    button.addEventListener('click',() => {
        if(button.style.borderStyle === 'none') {
            button.style.borderStyle = 'solid';
            button.style.borderColor = '#474747';
        } else{
            button.style.borderStyle = 'none';
        }
    });

});

oddButtons.forEach(button =>{
    button.addEventListener('click',() => {
        const ul = divList.querySelector('ul');
        const li = document.createElement('li');

        if(button.style.backgroundColor === 'rgb(238, 119, 66)'){
            // Button selected
            // Remove gameID from gamesIds selected
            const gameId =  button.querySelector('#gameId').value;
            let index = gamesIds.indexOf(gameId);
            if (index > -1) { // only splice array when item is found
                gamesIds.splice(index, 1); // 2nd parameter means remove one item only
            }
            button.style.backgroundColor = '#ffffff';
            button.style.borderColor = '#16350e';
            // Get element with id #homeTeam#awayTeam#choosenTeam
            const child = document.querySelector('#' +  button.querySelector('#homeTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#awayTeam').value.replace(/[^A-Z0-9]+/ig, "_") + button.querySelector('#choosenTeam').value);
            ul.removeChild(child);
            index = odds.indexOf(parseFloat(button.querySelector("#odd").value));
            if (index > -1) { // only splice array when item is found
                odds.splice(index, 1); // 2nd parameter means remove one item only
            }
            odds.indexOf(parseFloat(button.querySelector("#odd").value));
            oddTotal = calculateOddTotal(odds);
            document.querySelector(".oddTotal").innerHTML = oddTotal + (betType === 'simples' ? ' ( soma das odds)' : ' ( produto das odds)');
        } else {
            // Button not selected
            const gameId =  button.querySelector('#gameId').value;
            if(gamesIds.includes(gameId) && betType === 'multipla'){
                alert("Nao e possivel escolher dois resultados diferentes no mesmo jogo nas apostas multiplas");
                return;
            }
            gamesIds.push(gameId);
            button.style.backgroundColor = '#ee7742';
            button.style.borderColor = '#ee7742';
            bettedValue = parseFloat(document.querySelector('#bettedValue').value);
            odds.push(parseFloat(button.querySelector("#odd").value));
            console.log("Odd lida: " + parseFloat(button.querySelector("#odd").value));
            oddTotal = calculateOddTotal(odds);
            document.querySelector(".oddTotal").innerHTML = oddTotal + (betType === 'simples' ? ' ( soma das odds)' : ' ( produto das odds)');
            switch (currency){
                case "euros":
                    document.querySelector(".possibleGain").innerHTML = possibleGain + " €";
                    break;
                default:
                    document.querySelector(".possibleGain").innerHTML = possibleGain + " $";
                    break;
            }
            document.querySelector('#possibleGain').value = possibleGain;
            document.querySelector('#oddTotal').value = oddTotal;

            li.innerHTML = '<div class="aposta mb-1 px-4">'
                + '<b>' + button.querySelector('#homeTeam').value + '-' + button.querySelector('#awayTeam').value + '</b>'
                + '</div> <div class="aposta px-4 mb-4">'
                + '<br> Resultado Aposta: <b> ' +  button.innerHTML.split('<br>')[0] + '</b>'
                + '<div class="container"> Odd: <b>' + parseFloat(button.querySelector("#odd").value) + '</b></div>'
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
    document.querySelector('#possibleGain').value = possibleGain;
    document.querySelector('#oddTotal').value = oddTotal;
    switch (currency){
        case "euros":
            document.querySelector(".possibleGain").innerHTML = possibleGain + " €";
            break;
        default:
            document.querySelector(".possibleGain").innerHTML = possibleGain + " $";
            break;
    }
});

const selectCurrency = document.querySelector("#currency");

selectCurrency.addEventListener('change',() => {
   currency = selectCurrency.value;
    switch (currency){
        case "euros":
            document.querySelector(".possibleGain").innerHTML = possibleGain + " €";
            break;
        default:
            document.querySelector(".possibleGain").innerHTML = possibleGain + " $";
            break;
    }

});

betBtn.addEventListener('click', () => {
    const confirm = document.querySelector('#bettedValue');
    switch (currency){
        case "euros":
            document.querySelector('.possibleGain').innerHTML = possibleGain + " €";
            if (gamesIds.length === 0){
                confirm.setCustomValidity('Selecione pelo menos um jogo');
            } else if( bettedValue > euros ) {
                confirm.setCustomValidity('Saldo Insuficiente');
            } else {
                confirm.setCustomValidity('');
            }
            break;
        default:
            document.querySelector('.possibleGain').innerHTML = possibleGain + " $";
            if (gamesIds.length === 0){
                confirm.setCustomValidity('Selecione pelo menos um jogo');
            } else if( bettedValue > dollars ) {
                confirm.setCustomValidity('Saldo Insuficiente');
            } else {
                confirm.setCustomValidity('');
            }
            break;
    }
});

function hasDuplicates(array) {
    return (new Set(array)).size !== array.length;
}

function calculateOddTotal(odds){
    let oddTotal;
    switch (betType){
        case "simples":
            console.log("Somando odds: ");
            oddTotal = odds.reduce((a,b) => a+b,0);
            break;
        default:
            oddTotal = odds.reduce((a,b) => a*b,1);
            break;
    }
    return oddTotal;
}