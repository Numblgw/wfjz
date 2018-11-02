var spaUtil = (function() {
    /**
     * 发送ajax请求，只用于单页应用请求静态的dom和dom中的数据,数据格式为Json
     * @param method    请求dom使用get，请求数据使用post
     * @param url   路径的url
     * @param callback  响应结束后执行的函数
     * @param param     当请求dom时，param为dom的插入点，当请求数据时，param为向服务器端发送的参数
     */
    function ajaxRequest(method,url,callback,param){
        let xhr = new XMLHttpRequest();
        xhr.open(method,url,true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if(method.toLowerCase() == "get"){
                    callback(xhr,param);
                }
                if(method.toLowerCase() == "post"){
                    callback(xhr);
                }
            }
        };
        if (method.toLowerCase() == "get") {
            xhr.send(null);
        }
        if (method.toLowerCase() == "post") {
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send(param);
        }
    }

    /**
     * 获得单页应用中需要的dom
     * @param url   静态dom的路径
     * @param insertPoint   dom插入点
     */
    function getPage(url,insertPoint){
        ajaxRequest('get',url,(xhr,insertPoint)=>{
            insertPoint.innerHTML = xhr.responseText;
        },insertPoint);
    }

    return{getPage : getPage}
})();