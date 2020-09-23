layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //新闻列表url : '/news/searchNews',
    var searchValue = $('#searchVal').val();
    var tableInfo = table.render({
        elem: '#newsListTable',
        url: '/news/searchNews',
        toolbar: true,
        title: '用户数据表',
        totalRow: true,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        where: {newsName: searchValue},
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'id', title: 'ID', width: 60, align: "center"},
            {field: 'newsName', title: '文章标题', width: 350},
            {field: 'newsAuthor', title: '发布者', align: 'center'},
            {field: 'newsStatus', title: '发布状态', align: 'center', templet: "#newsStatus"},
            {field: 'newsLook', title: '浏览权限', align: 'center'},
            {
                field: 'newsTop', title: '是否置顶', align: 'center', templet: function (d) {
                    return '<input type="checkbox" name="newsTop" value="'+d.id +'" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" ' + d.newsTop + '>'
                }
            },
            {
                field: 'newsTime', title: '发布时间', align: 'center', minWidth: 110, templet: function (d) {
                    return d.newsTime.substring(0, 10);
                }
            },
            {title: '操作', width: 170, templet: '#newsListBar', fixed: "right", align: "center"}
        ]],
        parseData:
            function (res) { //将原始数据解析成 table 组件所规定的数据
                console.log(res)
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.list //解析数据列表
                };
            }
    });


    //是否置顶
    form.on('switch(newsTop)', function (data) {
        console.log(data.value)
        var index = layer.msg('修改中，请稍候', {icon: 16, time: false, shade: 0.8});
        updateTop(data);
        setTimeout(function () {
            layer.close(index);
            if (data.elem.checked) {
                layer.msg("置顶成功！");
            } else {
                layer.msg("取消置顶成功！");
            }
        }, 500);
    })

    function updateTop(data){
        $.post("/news/updateTop", {
            id:data.value,
            newsTop:data.elem.checked ? "checked":"unchecked"
        },function (res) {
            console.log(res)
        })
    }

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        var searchValue = $(".searchVal").val();
        if (searchValue != '') {
            table.reload('newsListTable', {
                url: '/news/searchNews',

                where: {newsName: searchValue}, //设定异步数据接口的额外参数
                //,height: 300
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加文章
    function addNews(edit) {
        var index = layui.layer.open({
            title: "添加文章",
            type: 2,
            content: "/newsAdd",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    console.log(edit)
                    body.find(".newsName").val(edit.newsName);
                    body.find(".abstract").val(edit.newsAbstract);
                    body.find(".thumbImg").attr("src", edit.newsImg);
                    body.find(".layui-hide").val(edit.content);
                    body.find(".newsStatus select").val(edit.newsStatus);
                    body.find(".openness input[name='openness'][title='" + edit.newsLook + "']").prop("checked", "checked");
                    body.find("#top").prop("checked", edit.newsTop);
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }



    //编辑文章
    function updateNews(edit) {
        var index = layui.layer.open({
            title: "编辑文章",
            type: 2,
            content: "/newsUpdate",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    console.log(edit)
                    body.find("#newsId").val(edit.id);
                    body.find(".newsName").val(edit.newsName);
                    body.find(".abstract").val(edit.newsAbstract);
                    body.find(".thumbImg").attr("src", edit.newsImg);
                    body.find(".layui-hide").val(edit.content);
                    body.find("#newsStatusID").val(edit.newsStatus);
                    body.find(".openness input[name='openness'][title='" + edit.newsLook + "']").prop("checked", "checked");
                    console.log(edit.newsTop)
                    console.log(edit.newsTop == "checked" ? true:false)
                    body.find("#newsTopID").attr("checked", "checked");
                    form.render();
                    form.render("select");
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    $(".addNews_btn").click(function () {
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            ids = "";
        if (data.length > 0) {
            for (var i in data) {
                ids = ids + data[i].id + ","

            }
            console.log(ids)
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/news/deleteAllNews",{
                    ids : ids  //将需要删除的newsId作为参数传入
                },function(data){
                    var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("文章删除成功！");
                    }, 1000);
                    tableInfo.reload();
                })
            })
        } else {
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(newsList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            var s = JSON.stringify(data);
            console.log("--------"+s)
            console.log('++++'+data)

            window.sessionStorage.setItem('updateNews',s);
            window.location.href = "/newsUpdate";
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此文章？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/news/deleteNews",{
                    id : data.id
                },function(){
                    var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("文章删除成功！");
                    }, 1000);
                    tableInfo.reload();
                    layer.close(index);
                })
            });
        } else if (layEvent === 'look') { //预览
            var content = data.content;
            layer.alert(content)
        }
    });

})