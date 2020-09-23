layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)",function(data){

        console.log(data.field)

        // 实际使用时的提交信息
        $.post("/admin/addAdmin",{
            adminName : $(".userName").val(),  //登录名
            adminPhone : $(".userEmail").val(),  //邮箱
            adminGender : data.field.adminGender,  //性别
            adminType : data.field.userGrade,  //会员等级
            adminStatus : data.field.userStatus,    //用户状态
            adminAddTime : submitTime    //添加时间
        },function(res){
            console.log("iii"+res);
            var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
                top.layer.close(index);
                top.layer.msg("用户添加成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            },500);
            // window.history.back();
        })
        // setTimeout(function(){
        //     top.layer.close(index);
        //     top.layer.msg("用户添加成功！");
        //     layer.closeAll("iframe");
        //     //刷新父页面
        //     parent.location.reload();
        // },2000);
        return false;
    })

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})