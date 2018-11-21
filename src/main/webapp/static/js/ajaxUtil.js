var ajaxUtil = (function () {
    function ajaxRequest(method,url,callback,param) {
        let xhr = new XMLHttpRequest();
        xhr.open(method, url, true);
        xhr.onreadystatechange = ()=>{
            if (xhr.readyState == 4 && xhr.status == 200) {
                callback(xhr);
            }
        };
        if (method.toLowerCase() == "get") {
            xhr.send();
        }
        if (method.toLowerCase() == "post" || method.toLowerCase() == 'put' || method.toLowerCase() == 'delete') {
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.send(JSON.stringify(param));
        }
    }
    return {ajaxRequest : ajaxRequest}
})();