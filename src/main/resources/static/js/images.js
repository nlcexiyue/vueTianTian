layui.config({
	base : "../../js/"
}).use(['flow','form','layer','upload'],function(){
    var flow = layui.flow,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        $ = layui.jquery;

    //流加载图片
    var imgNums = 15;  //单页显示图片数量
    flow.load({
        elem: '#Images', //流加载容器
        done: function(page, next){ //加载下一页
            $.post("/images/selectImagesAll",function(res){
                //模拟插入
                var imgList = [],data = res.data;
                var maxPage = imgNums*page < data.list.length ? imgNums*page : data.list.length;
                setTimeout(function(){
                    for(var i=imgNums*(page-1); i<maxPage; i++){
                        imgList.push('<li><img layer-src="'+ data.list[i].path +'" src='+ data.list[i].path +' alt="'+data.list[i].name+'">' +
                            '<div class="operate"><div class="check">' +
                            '<input type="checkbox" name="'+data.list[i].id+'" lay-filter="choose" lay-skin="primary" title="'+data.list[i].name+'">' +
                            '</div><i class="layui-icon img_del">&#xe640;</i></div></li>');
                    }
                    next(imgList.join(''), page < (data.list.length/imgNums));
                    form.render();
                }, 500);
            });
        }
    });

    //设置图片的高度
    $(window).resize(function(){
        $("#Images li img").height($("#Images li img").width());
    })

    //多图片上传
    upload.render({
        elem: '.uploadNewImg',
        url: '/images/uploadImages',
        multiple: true,
        before: function(obj){
            //预读本地文件示例，不支持ie8
            console.log(obj);
            obj.preview(function(index, file, result){
                $('#Images').prepend('<li><img layer-src="'+ result +'" src="'+ result +'" alt="'+ file.id +'" class="layui-upload-img">' +
                    '<div class="operate"><div class="check">' +
                    '<input type="checkbox" name="belle" lay-filter="choose" type="'+file.id+'" lay-skin="primary" title="'+file.name+'">' +
                    '</div><i class="layui-icon img_del" type="'+file.id+'">&#xe640;</i></div></li>')
                //设置图片的高度
                $("#Images li img").height($("#Images li img").width());
                form.render("checkbox");
            });
        },
        done: function(res){
            //上传完毕
            window.location.href="/images"
        }
    });

    //弹出层
    $("body").on("click","#Images img",function(){

        layer.photos({
            photos: '.layer-photos-demo',
            anim: 5
        });
    })

    //删除单张图片
    $("body").on("click",".img_del",function(){
        var _this = $(this);

        var id = _this.siblings().find("input").attr("name");
        layer.confirm('确定删除图片"'+_this.siblings().find("input").attr("title")+'"吗？',{icon:3, title:'提示信息'},function(index){
            _this.parents("li").hide(1000);
            deleteImages(id);
            setTimeout(function(){_this.parents("li").remove();},950);
            layer.close(index);
        });
    })
    var ids = '';
    //全选
    form.on('checkbox(selectAll)', function(data){
        var child = $("#Images li input[type='checkbox']");
        child.each(function(index, item){
            item.checked = data.elem.checked;
            var attr = $(item).attr("name");
            ids = ids +","+ attr;
        });
        ids = ids.substring(1,ids.length);
        console.log("---"+ids)
        form.render('checkbox');
    });

    //通过判断是否全部选中来确定全选按钮是否选中
    form.on("checkbox(choose)",function(data){
        var child = $(data.elem).parents('#Images').find('li input[type="checkbox"]');
        var childChecked = $(data.elem).parents('#Images').find('li input[type="checkbox"]:checked');
        if(childChecked.length == child.length){
            $(data.elem).parents('#Images').siblings("blockquote").find('input#selectAll').get(0).checked = true;
        }else{
            $(data.elem).parents('#Images').siblings("blockquote").find('input#selectAll').get(0).checked = false;
        }
        form.render('checkbox');
    })



    //批量删除
    $(".batchDel").click(function(){
        var $checkbox = $('#Images li input[type="checkbox"]');
        var $checked = $('#Images li input[type="checkbox"]:checked');
        if($checkbox.is(":checked")){
            layer.confirm('确定删除选中的图片？',{icon:3, title:'提示信息'},function(index){

                var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                deleteImages(ids)
                console.log("5555"+ids)
                setTimeout(function(){
                    //删除数据
                    $checked.each(function(){
                        $(this).parents("li").hide(1000);
                        setTimeout(function(){$(this).parents("li").remove();},950);
                    })
                    $('#Images li input[type="checkbox"],#selectAll').prop("checked",false);
                    form.render();

                    layer.close(index);
                    layer.msg("删除成功");
                },2000);
            })
        }else{
            layer.msg("请选择需要删除的图片");
        }
    })


})
function deleteImages(id) {
    $.post("/images/deleteImagesById",{id:id},function (res) {

    })
}


