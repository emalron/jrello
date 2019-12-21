var ajaxCall = function(url, formID, callback) {
    
    var req = new XMLHttpRequest();
                
    req.onreadystatechange = function() {
        if(req.readyState == req.DONE) {
            if(req.status == 200 || req.status == 201) {
                console.log(req.responseText);
                if(req.responseText != "") {
                    callback(req.responseText);
                }
            }
            else {
                console.error(req.responseText);
            }
        }
    }

    var str = makeSTR(formID);

    req.open('post', url);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(str);
}

var makeSTR = function(cmd) {
    let output = "";

    if(cmd == "") {
        output = "cmd=loginCheck";    
    }
    else if(cmd == "test") {
        output = "cmd=test";
    }
    else {
        var fData = new FormData(document.getElementById(cmd));
        var temp = [];
        for(var [key, value] of fData.entries()) {
            temp.push({key: key, value: value});
        }

        output = temp[0].key + "=" + temp[0].value;

        for(var i=1; i<temp.length; i++) {
            output += "&" + temp[i].key + "=" + temp[i].value;
        }
    }

    return output;
}

var login_success = function(responseText) {
    var slot = document.getElementById("output");

    slot.innerHTML = responseText;
}

var show_members_data = function(responseText) {
    let obj = JSON.parse(responseText);
    let target = document.getElementById("output");
    target.innerHTML = "";

    console.log(obj);

    target.innerHTML = obj.reduce((acc, cur) => acc += `${cur.id} / ${cur.password} / ${cur.name}<br>`, "");
}