(layui.use('table', function(){
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#userList'
        ,height: 312
        ,url: '/admin/userList' //数据接口
        ,method:'post'
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "data": res.data //解析数据列表
            };
        }
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
            ,{field: 'username', title: '用户名', width:80}
        ]]
    });
}))()