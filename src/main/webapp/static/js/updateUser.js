//获得数据并封装为对象，只获得修改过的数据
function getData(){
    let userData = new Object();
    document.querySelectorAll('.updateUser-info-after input').forEach((input)=>{
        let id = input.id;
        let value = input.value;
        if(id == 'updateUser-input-id'){
            let beforeValue = window.stringUtil.trim(document.querySelectorAll('.updateUser-before-id')[0].innerHTML);
            if(value.length != 0){
                userData.id = value;
            } else if(beforeValue != ''){
                userData.id = beforeValue;
            } else{}
        } else if(id == 'updateUser-input-username'){
            let beforeValue = window.stringUtil.trim(document.querySelectorAll('.updateUser-before-username')[0].innerHTML);
            if(value.length != 0){
                userData.username = value;
            } else if(beforeValue != ''){
                userData.username = beforeValue;
            } else{}
        } else if(id == 'updateUser-input-nickname'){
            let beforeValue = window.stringUtil.trim(document.querySelectorAll('.updateUser-before-nickname')[0].innerHTML);
            if(value.length != 0 && value != beforeValue){
                userData.nickname = value;
            }
        } else if(id == 'updateUser-input-sex'){
            let beforeValue = window.stringUtil.trim(document.querySelectorAll('.updateUser-before-sex')[0].innerHTML);
            if(value.length != 0 && value !=  beforeValue){
                userData.sex = value;
            }
        } else if(id == 'updateUser-input-email'){
            let beforeValue = window.stringUtil.trim(document.querySelectorAll('.updateUser-before-email')[0].innerHTML);
            if(value.length != 0 && value !=  beforeValue){
                userData.email = value;
            }
        } else if(id == 'updateUser-input-phone'){
            let beforeValue = window.stringUtil.trim(document.querySelectorAll('.updateUser-before-phone')[0].innerHTML);
            if(value.length != 0 && value !=  beforeValue){
                userData.phone = value;
            }
        } else if(id == 'updateUser-input-address'){
            let beforeValue = window.stringUtil.trim(document.querySelectorAll('.updateUser-before-address')[0].innerHTML);
            if(value.length != 0 && value !=  beforeValue){
                userData.address = value;
            }
        } else{}
    });
    return userData;
}

function checkData(userData){
    if(userData.id != undefined || userData.username != undefined || userData.nickname != undefined || userData.sex != undefined || userData.email != undefined || userData.phone != undefined || userData.address != undefined){
        if(userData.id != undefined){
            let regular = /^[0-9]*$/;
            if(!regular.test(userData.id)){
                alert('用户id必须为数字')
                return false;
            }
        }
        if(userData.username != undefined && (userData.username.length <= 4 || userData.username.length >= 20)) {
            alert('用户名必须为4~20位字符');
            return false;
        }
        if(userData.nickname != undefined && userData.nickname.length > 32){
            alert('昵称必须小于32位字符');
            return false;
        }
        if(userData.sex != undefined && userData.sex != 1 && userData.sex != 0){
            alert('性别必须为1或者0');
            return false;
        }
        if(userData.email != undefined){
            let regular = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            if(!regular.test(userData.email)){
                alert('邮箱必须符合邮箱格式');
                return false;
            }
        }
        if(userData.phone != undefined){
            let regular = /^[0-9]*$/;
            if(!regular.test(userData.phone)){
                alert('电话必须为数字')
                return false;
            }
        }
        return true;
    } else{
        alert('不能为空！');
        return false;
    }
}


(function init(){
    let updateId = 0;

    //监听提交按钮的点击事件
    document.querySelectorAll('.updateUser-button-submit')[0].addEventListener('click',()=>{
        let userData = getData();
        console.log(userData);
        if(checkData(userData)){
            window.ajaxUtil.ajaxRequest('put','/admin/updateUser',(xhr)=>{
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

    document.getElementById('updateUser-input-id').addEventListener('blur',(event)=>{
        let idInput = event.target;
        let value = idInput.value;
        if(value != updateId){
            updateId = value;
            let url = `/admin/userInfo?id=${value}`;
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

    document.getElementById('updateUser-input-username').addEventListener('blur',()=>{

    },false);
})();

