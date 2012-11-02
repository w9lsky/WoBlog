$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
$(document).ready(function() {
    $('#submit_comment').click(function() {
        var comment = $('#comment_form').serializeObject();
        $('#name_error').html('');
        $('#mail_error').html('');
         $('#content_error').html('');
        if(comment.name=="" || comment.name.length>15){
            $('#name_error').html("not valid name");
            return;
        }

        var mailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!mailReg.test(comment.mail)) {
            $('#mail_error').html("not valid mail");
            return;
        }

        if (comment.content == "" || comment.name.length > 140) {
            $('#content_error').html("not valid content");
            return;
        }
        var commentJSON = $.toJSON(comment);

        // before ajax
        $('#submit_comment').ajaxStart(function() {
            $('#submit_comment').attr('disabled', true);
        });

        // start ajax
        $.ajax({
            url : 'addComment?postId=' + $('#post_id').val(),
            type : 'post',
            contentType : 'application/json',
            data : commentJSON,
            dataType : 'json',
            success:function(data, htmlStatus, jqXHR) {
                afteAddComment(data, category, textStatus, jqXHR);
            },
            error : function(jqXHR, textStatus, errorThrown) {
                alert(textStatus);
            },
            complete:function(jqXHR, textStatus) {
                $('#submit_comment').attr('disabled', false);
            }
        });

    });

});