var br = {};
br.spTags = ["img","br","hr"];/*不需要成对出现的标记*/
br.contain = function(arr,it){
    for(var i=0,len=arr.length;i<len;i++){
        if(arr[i]==it){
            return true;
        }
    }
    return false;
}
br.subArtc = function(article,worldNum){
    var result = [];
    /*首先截取需要的字串*/
    var wcount = 0;
    var startTags = [],endTags = [];
    var isInTag = false;
    for(var i=0,len=article.length;i<len;i++){
        var w = article[i];
        result.push(w);
        if(w=="<"){
            isInTag = true;
        }
        if(!isInTag){
            wcount++;
            if(wcount==worldNum){
                break;
            }
        }
        if(w==">"){
            isInTag = false;
        }
    }
    /*对字串进行处理*/
    var j=0;
    isInTag = false;
    var isStartTag = true;
    var tagTemp = "";
    while(j<i){
        w = result[j];
        if(isInTag){
            if(w==">" || w==" " || w=="/"){
                isInTag = false;
                if(isStartTag){
                    startTags.push(tagTemp);
                }else{
                    endTags.push(tagTemp);
                }
                tagTemp = "";
            }
            if(isInTag){
                tagTemp+=w;
            }
        }
        if(w=="<"){
            isInTag = true;
            if(result[j+1]=="/"){
                isStartTag = false;
                j++;
            }else{
                isStartTag = true;
            }
        }
        j++;
    }
    /*剔除img,br等不需要成对出现的标记*/
    var newStartTags = [];
    for(var x=0,len=startTags.length;x<len;x++){
        if(!br.contain(br.spTags,startTags[x])){
            newStartTags.push(startTags[x]);
        }
    }
    /*添加没有的结束标记*/
    var unEndTagsCount = newStartTags.length - endTags.length;
    while(unEndTagsCount>0){
        result.push("<");
        result.push("/")
        result.push(newStartTags[unEndTagsCount-1]);
        result.push(">");
        unEndTagsCount--;
    }
    return result.join("");
};
$(document).ready(function() {
    // KindEditor initialization
    KindEditor.ready(function(K) {
        var editor = K.create('#content',{
            afterBlur:function(){
                $('#excerpt').val(br.subArtc($('#content').val(),500));
            }
        });
    });


    //wbox initialization
    $('.postbox').each(function() {
        var box = this;
        $(box).children('.handlep').click(function() {
            $(box).children('.inside').toggle('fast');
        });
        $(box).children('.handlediv').click(function() {
            $(box).children('.inside').toggle('fast');
        });
    });

    // category list initialization
    var categoryDiv = "#category_inside";
    $(categoryDiv).html("loading...");
    $.get('wb_admin/ajaxListTerm?taxonomy=category', null, function (data, textStatus) {
        $(categoryDiv).html('');
        $.each(data.successList, function(key, value) {
            if ($(categoryDiv).html().trim() == '') {
                $(categoryDiv).append("<ul id='id_" + value.parent + "'></ul>");
            } else if ($(categoryDiv + " ul[id=id_" + value.parent + "]").size() == 0) {
                $(categoryDiv + " li[id=id_" + value.parent + "]").append("<ul id='id_" + value.parent + "'></ul>");
            }

            $(categoryDiv + " ul[id=id_" + value.parent + "]").append("<li id='id_" + value.id + "'><label>" +
                "<input type='checkbox' id='" + value.id + "' value='" + value.id + "' name='PostTerms'/>" + value.name + "</label></li>");
        });

        $('[name=temp_category_id]').each(function(){
            $('#'+$(this).val()).attr('checked', true);
        });
    });

    // tag cloud handler
    $('#tag_cloud_link').click(function(){
        $.get('wb_admin/ajaxListTerm?taxonomy=tag', null, function (data, textStatus) {
            $.each(data.successList, function(key, value) {
                $("#tag_cloud_div").append("<a href='javascript:void(0)' onclick=\"linkTagHandler('" + value.id + "','" + value.name + "')\">" + value.name + "</a>");
            });
        });
        $('#tag_cloud_link').unbind("click");
    });

    // add new tag
    $('#add_tag_btn').click(function(){
        var tagName = $('#tag_text').val();
        //"NEW_TAG_" is defined in Constants.java.
        linkTagHandler('NEW_TAG_'+tagName,tagName);
    });
});

function linkTagHandler(id, name) {
    if ($('#tag_label_' + id).length == 0) {
        $('#tag_div').append("<label id='tag_label_" + id + "'>" +
            "<a href='javascript:void(0)' onclick=\"javascript:$('#tag_label_" + id + "').remove();\" class='remove_tag'>" +
            "<span>" + name + "</span>" +
            "</a>" +
            "<input type='hidden' name='PostTerms' value='" + id + "'>" +
            "</label>");
    }
}

/*
 * 调整截取的字符串的位置。因为在截取一个字符串后可能存在两种情况：
 * （1）字符串末位正好处于一个标签的属性之中的某个位置。如：<<span style="color: #ff0000;">a id="xxx"  href="xxx"</span>
 ></a>中红色标注的位置。
 &nbsp;* （2）字符串末位处于一个标签内容之中的某个位置。如：<<span style="color: #000000;">a</span>
 ><span style="color: #ff0000;">here</span>
 </a> 中红色标注的位置。
 *  对于这两种情况，第二种情况可以无需特殊处理，但是第一种情况的话得将整个标签删除或者将其属性补全。删除的话
 *  将字符串的末尾位置前移至标签开始之前；而补全则是将末尾位置后移至次标签的'>'处。这里采用的是删除的方法。
 */


