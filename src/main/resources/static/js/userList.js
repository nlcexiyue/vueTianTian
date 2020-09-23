layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '/admin/adminList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'adminName', title: '用户名', minWidth:100, align:"center"},
            {field: 'adminPhone', title: '用户邮箱', minWidth:200, align:'center',templet:function(d){
                return '<a class="layui-blue" href="mailto:'+d.adminPhone+'">'+d.adminPhone+'</a>';
            }},
            {field: 'adminGender', title: '用户性别', align:'center', templet:function (d) {
                    if(d.adminGender == "0"){
                        return "女";
                    }else if(d.adminGender == "1"){
                        return "男";
                    }else if(d.adminGender == "2"){
                        return "不清楚";
                    }
            }},
            {field: 'adminStatus', title: '用户状态',  align:'center',templet:function(d){
                return d.adminStatus == "1" ? "正常使用" : "限制使用";
            }},
            {field: 'adminType', title: '用户等级', align:'center',templet:function(d){
                if(d.adminType == "0"){
                    return "注册会员";
                }else if(d.adminType == "1"){
                    return "中级会员";
                }else if(d.adminType == "2"){
                    return "高级会员";
                }else if(d.adminType == "3"){
                    return "钻石会员";
                }else if(d.adminType == "4"){
                    return "超级会员";
                }
            }},
            {field: 'adminAddTime', title: '最后登录时间', align:'center',minWidth:150},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]],
        parseData:function (res) {
            // for (var i = 0; i < res.data.list.length; i++) {
            //     res.data.list[i].adminName = res.data.list[i].adminName;
            // }
            return {
                'data' : res.data.list,
                'code' : res.code,
                'msg' : res.msg,
                'count' : res.data.total
            }
        }
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("newsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "/userAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".adminName").val(edit.userName);  //登录名
                    body.find(".adminPhone").val(edit.userEmail);  //邮箱
                    body.find(".adminGender input[value="+edit.userSex+"]").prop("checked","checked");  //性别
                    body.find(".adminType").val(edit.userGrade);  //会员等级
                    body.find(".adminStatus").val(edit.userStatus);    //用户状态
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                    tableIns.reload();
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,

            ids = '';
        console.log(data)
        if(data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                if (i == data.length-1){
                    ids = ids + data[i].id;
                } else{
                    ids = ids + data[i].id + ',';
                }
            }

            console.log(ids)
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/admin/deleteAll",{
                    ids : ids  //将需要删除的newsId作为参数传入
                },function(data){
                    var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("用户删除成功！");
                    }, 1000);
                tableIns.reload();
                layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            var s = JSON.stringify(data);
            console.log("--------"+s)
            console.log('++++'+data)

            window.sessionStorage.setItem('updateUser',s);
            window.location.href = "/userUpdate";

            // addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this);
            console.log(_this.text())
            if(data.adminStatus == 1){
                var usableText = "是否确定禁用此用户？",
                    status = 2,
                    btnText = "已禁用";
            }
            if(data.adminStatus == 2){
                usableText = "是否确定启用此用户？",
                    status = 1,
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                $.post("/admin/updateStatus",{
                    //将需要删除的newsId作为参数传入
                    id : data.id,
                    adminStatus : status
                },function(data){
                    _this.text(btnText);
                    tableIns.reload();
                    layer.close(index);
                })
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                $.post("/admin/deleteOne",{
                    id : data.id  //将需要删除的newsId作为参数传入
                },function(data){
                    var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("用户删除成功！");
                    }, 1000);
                    tableIns.reload();
                })
            });
        }
    });

})
