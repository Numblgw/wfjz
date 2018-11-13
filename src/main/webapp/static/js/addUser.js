layui.use('form', function(){
    let form = layui.form;

    //监听提交
    form.on('submit(addUserForm)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});