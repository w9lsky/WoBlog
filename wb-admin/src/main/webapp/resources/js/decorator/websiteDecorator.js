$(document).ready(function() {
    $.get('ajaxListPost', null, function (data, textStatus) {
        $.each(data.successList, function(key, value) {
            $("#nav_post_div ul").append("<li><a href='?post=" + value.id + "' title='" + value.title + "'>" + value.title + "</a></li>");
        });
    });

    $.get('ajaxListTag', null, function (data, textStatus) {
        $.each(data.successList, function(key, value) {
                //var color = 0xff;

            $("#nav_tag_div").append("<a href='?tag=" + value.id + "' title='" + value.name + "'>" + value.name + "</a>");
        });
    });

    $.get('ajaxListComment', null, function (data, textStatus) {
        $.each(data.successList, function(key, value) {
            //value.createDate,value.authorName,value.post.title,value.content
            $("#nav_comment_div").append("<div>" + value.authorName + "对<a href='?post=" + value.post.id + "' title='" + value.post.title + "'>" + value.post.title + "</a>发表了评论：" + value.content + "</div>");
        });
    });
});