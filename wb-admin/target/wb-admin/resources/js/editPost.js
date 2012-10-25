$(document).ready(function() {
    // KindEditor initialization
    KindEditor.ready(function(K) {
        K.create('#content');
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

