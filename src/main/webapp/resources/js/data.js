/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function fridaysDisabled(date) {

    var days = JSON.parse(document.getElementById("w").value);
    var day = date.getDay();
    console.log(days);
    if ($.inArray(day, days) !== -1) {
        return [true, ''];
    }

    return [false, ''];
}

