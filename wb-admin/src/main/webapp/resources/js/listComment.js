$(document).ready(function() {
    // Make all checkbox checked
    $('.select_all_checkbox').click(function() {
        $('table :checkbox').attr('checked', this.checked);
    });

    $(":checkbox").each(function() {
        $(this).click(function() {
            if (!$(this).attr('checked')) {
                $('.select_all_checkbox').attr('checked', false);
            }

            if ($(':input[type=checkbox]').length - $(':checked[class!=select_all_checkbox]').length == 2) {
                $('.select_all_checkbox').attr('checked', true);
            }
        });
    });

    // row action
    $('tbody tr').mousemove(function() {
        $("#" + $(this).attr('id') + " .row-action").css('visibility', 'visible');
    });
    $('tbody tr').mouseout(function() {
        $("#" + $(this).attr('id') + " .row-action").css('visibility', 'hidden');
    });
});
function applyHandler(flag) {
    var objIdArray = new Array();
    if ($(':checked[type=checkbox]').length != 0) {
        var status = $('#operation_' + flag).val();
        if (!status) {
            addErrorMessage("请选择批量操作内容！")
            return;
        }

        // get the obj id array
        $('table :checkbox[class!=select_all_checkbox]').each(function () {
            if (this.checked) {
                var obj = new Object();
                obj.id = $(this).val();
                obj.commentStatus = $('status_' + $(this).val()).val();
                if (obj.commentStatus == status) {
                    return;
                }
                obj.postId = $('#postId_' + $(this).val()).val();
                objIdArray.push(obj);
            }
        });

        if (objIdArray.length == 0) {
            addErrorMessage("本来就是这个状态,还改什么啊!");
        } else {
            updateObj(objIdArray, null, status);
        }
    } else {
        addErrorMessage("至少需要选中一行数据！");
        return;
    }
}

function updateObj(obj, oStatus, nStatus) {

    if (oStatus == nStatus) {
        addErrorMessage("本来就是这个状态,还改什么啊!");
    }
    var objArray = new Array();
    if (obj instanceof Array) {
        objArray = obj;
        for (var i = 0; i < objArray.length; i++) {
            $(' table tr[id=' + objArray[i] + ']').css('background-color', 'red');
        }
    } else {
//        objArray.push(obj);
        var comment = new Object();
        comment.id = obj;
        comment.commentStatus = oStatus;
        comment.postId = $('#postId_' + obj).val();
        objArray.push(comment);
        $(' table tr[id=' + obj + ']').css('background-color', 'red');
    }

    $('.ajax_response').ajaxStart(function() {
        cleanErrorMessage();
        $('.applyBtn').attr('disabled', true);
    });

    $.ajax({
        url : 'wb_admin/editComment?status=' + nStatus,
        type : 'post',
        contentType : 'application/json',
        data : $.toJSON(objArray),
        dataType : 'json',
        success:function(data) {
//                if(data.jsonResult.success){
            addErrorMessage(data.jsonResult.message);
//                }
        },
        complete:function(jqXHR, textStatus) {
            $('.applyBtn').attr('disabled', false);
        }
    });
}


function cleanErrorMessage() {
    $('#ajax_response').html("<div class='error'></div>");
}

function addErrorMessage(msg) {
    $('#ajax_response').html("<div class='error'>" + msg + "</div>");
}

function addSuccessMessage(msg) {
    $('#ajax_response').html("<div class='success'>" + msg + "</div>");
}
