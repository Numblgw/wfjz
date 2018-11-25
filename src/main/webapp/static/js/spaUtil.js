var spaUtil = (function () {

    function loadJs(jsUrl) {
        let node = document.getElementsByTagName('body')[0];
        let scripts = node.getElementsByTagName('script');
        let script = document.createElement("script");
        if (Array.isArray(Array.from(scripts)) && scripts.length != 0) {
            Array.from(scripts).forEach((script) => {
                node.removeChild(script);
            });
        }
        script.src = jsUrl;
        script.type = "text/javascript";
        script.charset = "UTF-8";
        node.appendChild(script);
    }

    function getPageByAjax(method, domUrl, jsUrl, callback, insertPoint) {
        let xhr = new XMLHttpRequest();
        xhr.open(method, domUrl, true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                callback(xhr, insertPoint);
                if(jsUrl != null) {
                    loadJs(jsUrl);
                }
            }
        };
        xhr.send(null);
    }

    function getPage(domUrl, jsUrl, insertPoint) {
        getPageByAjax('get', domUrl, jsUrl, (xhr, insertPoint) => {
            insertPoint.innerHTML = xhr.responseText;
        }, insertPoint);
    }

    function setData(items,data){
        let keys = Object.keys(data);
        items.forEach((item)=>{
            keys.forEach((key)=>{
                let name = item.className.split('-');
                if(name[name.length-1] == key){
                    item.innerHTML = data[key];
                }
            });
        });
    }

    return {getPage : getPage , setData : setData}
})();