function validate() {
    let elements = document.forms[0].elements;
    for (let i = 0; i < elements.length - 1; i++) {
        if ($(elements[i]).val() === '') {
            alert($(elements[i]).attr('title'));
            return false;
        }
    }
    return true;
}

function showAllTask() {
    let currentPath = document.baseURI;
    let tasksOpen = document.location.protocol + "//" + window.location.host + "/todo/task";
    let tasksAll = document.location.protocol + "//" + window.location.host +"/todo/task?show=yes";
    if (currentPath === tasksOpen) {
        return window.location.href = "/todo/task?show=yes";
    } else if (currentPath === tasksAll) {
        return window.location.href = "/todo/task";
    }
}

window.onload = function(){
    let paramString = document.location.search;
    let searchParams = new URLSearchParams(paramString);
    if (searchParams.has("show")) {
        document.getElementById("showButton").innerHTML = "Показать открытые задачи";
    } else {
        document.getElementById("showButton").innerHTML = "Показать все задачи";
    }
}
