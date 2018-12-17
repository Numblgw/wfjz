//后台管理界面——用户管理——用户列表的js脚本

layui.use(['table','element','layer','laypage'], function(){
    let table = layui.table;

    table.render({
        elem: '#userList'
        ,url: '/user/userList' //数据接口
        ,method:'get'
        ,toolbar:'default'
        ,page:true
        ,limits:[5,10,15]
        ,none:'无数据'
        ,response: {
            statusCode: 200
        }
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.code,   //解析接口状态
                "msg": res.msg,     //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.list    //解析数据列表
            };
        }
        ,cols: [[ //表头
            {type:'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', minWidth:60, sort: true, fixed: 'left'}
            ,{field: 'username', title: '用户名', minWidth:80}
            ,{field: 'nickname', title: '昵称', minWidth:80}
            ,{field: 'sex', title: '性别', minWidth:80,sort:true}
            ,{field: 'email', title: '邮箱', minWidth:80}
            ,{field: 'phone', title: '电话', minWidth:80}
            ,{field: 'address', title: '地址', minWidth:80}
        ]]
    });

    table.on('toolbar(userList)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                document.querySelectorAll('.dd_addUser')[0].click();
                break;
            case 'update':
                if(data.length === 0){
                    alert('请选择一行');
                } else if(data.length > 1){
                    alert('只能同时编辑一个');
                } else {
                    document.querySelectorAll('.update-user-id')[0].innerHTML = checkStatus.data[0].id;
                    document.querySelectorAll('.dd_updateUser')[0].click();
                }
                break;
            case 'delete':
                if(data.length === 0){
                    alert('请选择一行');
                } else {
                    if(confirm('确定要删除吗')){
                        let ids = new Array();
                        checkStatus.data.forEach((obj)=>{
                            ids.push(obj.id);
                        });
                        window.ajaxUtil.ajaxRequest('delete','/user/deleteUser',(xhr)=>{
                            let jsonData = JSON.parse(xhr.responseText);
                            if(jsonData.code == 200){
                                alert('删除成功');
                                window.location.reload();
                            } else{
                                alert(jsonData.msg);
                            }
                        },ids)
                    }
                }
                break;
        };
    });
})