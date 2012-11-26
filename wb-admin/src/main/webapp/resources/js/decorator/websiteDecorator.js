$(document).ready(function() {
            $.get('ajaxListPost', null, function (data, textStatus) {
            $.each(data.successList, function(key, value) {
                $("#nav_post_div ul").append("<li><a href='?post="+value.id+"' title='"+value.title+"'>"+value.title+"</a></li>");
            });
        });
});