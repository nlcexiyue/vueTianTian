layui.config({ base: '/layui/textool.js' }).extend({
    'textool': 'textool'
})
layui.use(['form', 'layer', 'layedit', 'laydate', 'upload', 'element', 'textool'], function () {
    var form = layui.form,
        textool = layui.textool;

    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        element = layui.element,
        $ = layui.jquery;

    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);
    var object = $('#news_content').val();
    textool.init({
        // 根据元素 id 值单独渲染，为空默认根据 class='layext-text-tool' 批量渲染
        eleId: 'news_content',
        // 批量设置输入框最大长度，可结合 eleId 单独设置最大长度
        maxlength: -1,
        // 初始化回调，无参
        initEnd: function () {
            console.log('小公举')
        },
        // 显示回调，参数为当前输入框和工具条面板的 jQuery 对象
        showEnd: function (object) {
            console.log('小公举')
        },
        // 隐藏回调，参数为当前输入框和工具条面板的 jQuery 对象
        hideEnd:function (object) {
            console.log('小公举')
        },
        // 初始化展开，默认展开，否则收起
        initShow: true,
        // 启用指定工具模块，默认依次为字数统计、复制内容、重置内容、清空内容，按数组顺序显示
        tools: ['count', 'copy', 'reset', 'clear'],
        // 工具按钮提示类型，默认为 'title' 属性，可选 'laytips'，使用 layer 组件的吸附提示， 其他值不显示提示
        tipType: 'title',
        // 吸附提示背景颜色
        tipColor: '#01AAED',
        // 对齐方向，默认右对齐，可选左对齐 'left'
        align: 'right',
        // 工具条字体颜色
        color: '#666666',
        // 工具条背景颜色
        bgColor: '#FFFFFF',
        // 工具条边框颜色
        borderColor: '#E6E6E6',
        // 工具条附加样式类名
        className: '',
        // z-index
        zIndex: 19891014
    });
    //上传缩略图
    upload.render({
        elem: '.thumbBox',
        url: '/upload',
        accept: 'file',
        method: "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#preview').attr('src', result.data); //图片链接（base64）
            });
        },
        progress: function (n, elem) {
            var percent = n + '%' //获取进度百分比
            element.progress('speed', percent); //可配合 layui 进度条元素使用

            //以下系 layui 2.5.6 新增
            console.log(elem); //得到当前触发的元素 DOM 对象。可通过该元素定义的属性值匹配到对应的进度条。
        },
        done: function (res, index, upload) {
            //假设code=0代表上传成功
            if (res.code == 0) {
                //do something （比如将res返回的图片链接保存到表单的隐藏域）
                //上传完毕回调
                var num = parseInt(4 * Math.random());  //生成0-4的随机数，随机显示一个头像信息
                $('.thumbImg').attr('src', res.data);
                $('.thumbBox').css("background", "#fff");

            }else{

            }

        }, error: function () {
            //请求异常回调

        }
    });

    //格式化时间
    function filterTime(val) {
        if (val < 10) {
            return "0" + val;
        } else {
            return val;
        }
    }

    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1) + '-' + filterTime(time.getDate()) + ' ' + filterTime(time.getHours()) + ':' + filterTime(time.getMinutes()) + ':' + filterTime(time.getSeconds());
    laydate.render({
        elem: '#release',
        type: 'datetime',
        trigger: "click",
        done: function (value, date, endDate) {
            submitTime = value;
        }
    });
    form.on("radio(release)", function (data) {
        if (data.elem.title == "定时发布") {
            $(".releaseDate").removeClass("layui-hide");
            $(".releaseDate #release").attr("lay-verify", "required");
        } else {
            $(".releaseDate").addClass("layui-hide");
            $(".releaseDate #release").removeAttr("lay-verify");
            submitTime = time.getFullYear() + '-' + (time.getMonth() + 1) + '-' + time.getDate() + ' ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds();

        }
    });

    /*form.verify({
        newsName: function (val) {
            if (val == '') {
                return "文章标题不能为空";
            }
        },
        content: function (val) {
            if (val == '') {
                return "文章内容不能为空";
            }
        }
    })*/
    form.on("submit(addNews)", function (data) {
        //截取文章内容中的一部分文字放入文章摘要
        var abstract = layedit.getText(editIndex).substring(0, 50);
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post("/news/addNews",{
            newsName : $(".newsName").val(),  //文章标题
            newsAuthor : sessionStorage.getItem('user'),    //文章作者,当前登录状态
            newsAbstract : $(".abstract").val(),  //文章摘要
            content : layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0],  //文章内容
            newsImg : $(".thumbImg").attr("src"),  //缩略图
            classify : '1',    //文章分类
            newsStatus : $('.newsStatus select').val(),    //发布状态
            newsTime : submitTime,    //发布时间
            newsLook : $('.look').val(),    //文章预览状态
            newsTop : checkedSwitch  ? "checked" : "",    //是否置顶
        },function(res){
            console.log(res);
        })
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("文章添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 100);
        return false;
    })

    //监听指定开关
    var checkedSwitch;
    form.on('switch(switch)', function(data){
        checkedSwitch = data.elem.checked;
        console.log(data.elem.checked);
        layer.msg('开关checked：'+ (this.checked ? 'checked' : ''), {
            offset: '6px'
        });
        layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    });

    //预览
    form.on("submit(look)", function () {
        layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问");
        return false;
    })

    //创建一个编辑器
    var editIndex = layedit.build('news_content', {
        height: 535,
        uploadImage: {
            url: "../../json/newsImg.json"
        }
    });

})