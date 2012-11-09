$(document).ready(function() {
            var titleLen = 15;
            $.get('ajaxListPost', null, function (data, textStatus) {
            $.each(data.successList, function(key, value) {
                var title = value.title;

                if(title.length>titleLen){
                    var title = title.substr(0,titleLen)+"...";
                }
                $("#nav_post_div ul").append("<li><a href='?post="+value.id+"' title='"+value.title+"'>"+title+"</a></li>");
            });
        });
});