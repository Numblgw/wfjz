//获得数据并封装为对象，只获得修改过的数据
function getData(){
    let userData = {};
    document.querySelectorAll('.updateUser-info-after input').forEach((input)=>{
        let id = input.id;
        let value = input.value;
        let beforeValue = window.stringUtil.trim(document.querySelectorAll(`.updateUser-before-${id}`)[0].innerHTML);
        if(id == 'id' || id == 'username'){
            if(value.length != 0){
                userData[id] = value;
            } else{
                if(beforeValue != ''){
                    userData[id] = beforeValue;
                }
            }
        } else{
            if(value.length != 0 && value != beforeValue){
                userData[id] = value;
            }
        }
    });
    return userData;
}

//点击提交时校验对数据进行简单校验
function checkData(userData) {
    console.log(userData);
    //用户名和id必须填一个，后面需要修改的内容必须填一个
    if ((userData.id != undefined || userData.username != undefined) && (userData.nickname != undefined || userData.sex != undefined || userData.email != undefined || userData.phone != undefined || userData.address != undefined)) {
        if (userData.id != undefined) {
            let regular = /^[0-9]*$/;
            if (!regular.test(userData.id)) {
                alert('用户id必须为数字')
                return false;
            }
        }
        if (userData.username != undefined && (userData.username.length < 4 || userData.username.length > 20)) {
            alert('用户名必须为4~20位字符');
            return false;
        }
        if (userData.nickname != undefined && userData.nickname.length > 32) {
            alert('昵称必须小于32位字符');
            return false;
        }
        if (userData.sex != undefined && userData.sex != 1 && userData.sex != 0) {
            alert('性别必须为1或者0');
            return false;
        }
        if (userData.email != undefined) {
            let regular = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            if (!regular.test(userData.email)) {
                alert('邮箱必须符合邮箱格式');
                return false;
            }
        }
        if (userData.phone != undefined) {
            let regular = /^[0-9]*$/;
            if (!regular.test(userData.phone)) {
                alert('电话必须为数字')
                return false;
            }
        }
        return true;
    } else {
        alert('不能为空！');
        return false;
    }
}

//脚本程序主入口
(function init(){
    //判断是否通过用户列表跳转到修改页面
    let updateId = stringUtil.trim(document.querySelectorAll('.update-user-id')[0].innerHTML) == '' ? 0 : stringUtil.trim(document.querySelectorAll('.update-user-id')[0].innerHTML);
    let updateUsername = '';

    //如果通过用户列表跳转到的修改页面，则通过选择的id查询用户信息
    if(updateId != 0){
        let url = `/user/userInfo?id=${updateId}`;
        window.ajaxUtil.ajaxRequest('get',url,(xhr)=>{
            let jsonData = JSON.parse(xhr.responseText);
            if(jsonData.code == 200){
                window.spaUtil.setData(document.querySelectorAll('.updateUser-before'),jsonData.data);
                document.querySelectorAll('.updateUser-after-id input')[0].value = jsonData.data.id;
                document.querySelectorAll('.updateUser-after-id input')[0].readOnly = true;
                document.querySelectorAll('.updateUser-after-username input')[0].value = jsonData.data.username;
                document.querySelectorAll('.updateUser-after-username input')[0].readOnly = true;
            } else{
                alert(jsonData.msg);
            }
        });
    }

    //监听提交按钮的点击事件
    document.querySelectorAll('.updateUser-button-submit')[0].addEventListener('click',()=>{
        let userData = getData();
        if(checkData(userData)){
            window.ajaxUtil.ajaxRequest('put','/user/updateUser',(xhr)=>{
                let jsonData = JSON.parse(xhr.responseText);
                if(jsonData.code == 200){
                    alert('修改数据成功！');
                    window.location.reload();
                } else{
                    alert(jsonData.msg);
                }
            },userData);
        }
    },false);

    //id和username输入框添加失去焦点事件监听器，失去焦点时获取id或者用户名并提交服务器单查询
    document.querySelectorAll('.updateUser-after-id input')[0].addEventListener('blur',(event)=>{
        let idInput = event.target;
        let value = idInput.value;
        if(value != updateId){
            updateId = value;
            let url = `/user/userInfo?id=${value}`;
            window.ajaxUtil.ajaxRequest('get',url,(xhr)=>{
                let jsonData = JSON.parse(xhr.responseText);
                if(jsonData.code == 200){
                    window.spaUtil.setData(document.querySelectorAll('.updateUser-before'),jsonData.data);
                } else{
                    alert(jsonData.msg);
                }
            });
        }
    },false);
    document.querySelectorAll('.updateUser-after-username input')[0].addEventListener('blur',(event)=>{
        let username = event.target.value;
        let id = document.querySelectorAll('.updateUser-after-id input')[0].value;
        if(id == '' && username != updateUsername){
            updateUsername = username;
            let url = `/user/userInfo?id=0&username=${username}`;
            window.ajaxUtil.ajaxRequest('get',url,(xhr)=>{
                let jsonData = JSON.parse(xhr.responseText);
                if(jsonData.code == 200){
                    window.spaUtil.setData(document.querySelectorAll('.updateUser-before'),jsonData.data);
                } else{
                    alert(jsonData.msg);
                }
            });
        }
    },false);
})();

