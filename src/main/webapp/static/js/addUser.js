//后台管理界面——用户管理——添加用户的脚本

//获得表单中的数据封装在对象中
function getData(){
    let userData = new Object();
    userData.username = document.getElementById('username').value;
    userData.password = document.getElementById('password').value;
    userData.nickname = document.getElementById('nickname').value;
    userData.sex = document.getElementById('sex-male').checked == true ? 1 : 0;
    userData.email = document.getElementById('email').value;
    userData.phone = document.getElementById('phone').value;
    return userData;
}

//检查是否提交
function checkSubmit(flag){
    if(flag.username != undefined && flag.password != undefined && flag.rePassword != undefined){
        if(flag.username && flag.password && flag.rePassword){
            if(flag.nickname != false && flag.email != false && flag.phone != false){
                return true;
            }
        }
    }
    return false;
}

(function init(){
    //用户名的input框的值暂存，为对比input中用户名是否改变做准备
    let username = '';
    //标识表单当前是否验证通过
    let flag = new Object();
    //验证成功时的提示信息
    let truePrompt = '&nbsp;<i class="layui-icon layui-icon-ok-circle" style="color:green;font-size: 20px;"></i>';
    //验证失败是的提示信息前缀
    let falsePrompt = '&nbsp;<i class="layui-icon layui-icon-close-fill" style="color:#FF5722;font-size: 20px;"></i>';
    //未验证时的提示信息前缀
    let commonPrompt = '&nbsp;<i class="layui-icon layui-icon-tips" style="color: #FFB800;"></i>';
    //表单中所有的input
    let inputs = document.querySelectorAll('.addUser-input');

    inputs.forEach((input)=>{
        //input获得焦点时显示提示信息
        input.addEventListener('focus',(event)=>{
            //获得焦点的input
            let input = event.target;
            //input右边对应的提示信息的div
            let promptDiv = input.parentNode.nextElementSibling;
            if(input.id == 'username'){
                promptDiv.innerHTML = commonPrompt + '用户名必须为4~20位字符';
            } else if(input.id == 'password'){
                promptDiv.innerHTML = commonPrompt + '密码必须为6~20位字符';
            } else if(input.id == 're-password'){
                promptDiv.innerHTML = commonPrompt + '两次密码必须一致';
            } else if(input.id == 'nickname'){
                promptDiv.innerHTML = commonPrompt + '昵称长度必须在32个字符以内';
            } else if(input.id == 'email'){
                promptDiv.innerHTML = commonPrompt + '邮箱必须符合邮箱格式';
            } else if(input.id == 'phone'){
                promptDiv.innerHTML = commonPrompt + '电话必须符合电话格式';
            } else{

            }
        },false);
        //input失去焦点时验证并显示是否成功
        input.addEventListener('blur',(event)=>{
            //获得焦点的input
            let input = event.target;
            //input右边对应的提示信息的div
            let promptDiv = input.parentNode.nextElementSibling;
            //失去焦点时input中的值
            let value = input.value;
            //对用户名先进行普通验证，在进行ajax提交数据库验证
            if (input.id == 'username') {
                let url = `/user/usernameRepeat?username=${value}`;
                if (value != null && value != '' && value.length >= 4 && value.length <= 20) {
                    //与username比较，只有当用户名改变时，才进行ajax提交验证用户名是否存在
                    if(value != username){
                        username = value;
                        window.ajaxUtil.ajaxRequest('get',url,(xhr)=>{
                            let jsonData = JSON.parse(xhr.responseText);
                            //true表示用户名已存在，false表示用户名不存在
                            if(jsonData.data == false){
                                promptDiv.innerHTML = truePrompt;
                                flag.username = true;
                            } else{
                                promptDiv.innerHTML = falsePrompt + '用户名已存在';
                                flag.username = false;
                            }
                        });
                    }
                } else{
                    promptDiv.innerHTML = falsePrompt + '用户名格式不正确';
                    flag.username = false;
                }
                //密码格式校验
            } else if (input.id == 'password') {
                if(value.length >= 6 && value.length <= 20){
                    promptDiv.innerHTML = truePrompt;
                    flag.password = true;
                } else {
                    promptDiv.innerHTML = falsePrompt + '密码格式不正确';
                    flag.password = false;
                }
                //两次密码一致校验
            } else if (input.id == 're-password') {
                let password = document.getElementById('password').value;
                if(value != null && value != '' && password == value){
                    promptDiv.innerHTML = truePrompt;
                    flag.rePassword = true;
                } else{
                    promptDiv.innerHTML = falsePrompt + '两次输入密码不一致';
                    flag.rePassword = false;
                }
                //昵称校验
            } else if(input.id == 'nickname'){
                if(value.length <= 32){
                    promptDiv .innerHTML = truePrompt;
                    flag.nickname = true;
                } else{
                    promptDiv.innerHTML = falsePrompt + '昵称长度不合法';
                    flag.nickname = false;
                }
                //邮箱校验
            } else if (input.id == 'email') {
                //邮箱验证的正则表达式
                let regular = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
                if(!regular.test(value)){
                    promptDiv.innerHTML = falsePrompt + '邮箱格式不正确';
                    flag.email = false;
                } else{
                    promptDiv.innerHTML = truePrompt;
                    flag.email = true;
                }
                //电话校验
            } else if (input.id == 'phone') {
                //数字验证的正则表达式
                let regular = /^[0-9]*$/;
                if(!regular.test(value) || value == '' || value == null){
                    promptDiv.innerHTML = falsePrompt + '电话格式不正确';
                    flag.phone = false;
                } else{
                    promptDiv.innerHTML = truePrompt;
                    flag.phone = true;
                }
            } else{}
        },false);
    });

//提交时检验用户信息是否完善
    document.querySelectorAll('.addUser-button-submit')[0].addEventListener('click',()=>{
        if(checkSubmit(flag)){
            let userDetail = getData();
            console.log(userDetail);
            window.ajaxUtil.ajaxRequest('post','/user/addUser',(xhr)=>{
                let jsonData = JSON.parse(xhr.responseText);
                if(jsonData.code == 200){
                    alert('用户添加成功');
                    window.location.reload(true);
                } else{
                    alert(jsonData.msg);
                }
            },userDetail);
        } else{
            alert('请完善用户信息');
        }
    },false);
})()