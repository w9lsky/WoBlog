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

    // Apply event
    $('.applyBtn').click(applyHandler);

    function applyHandler() {
        var objIdArray = new Array();
        if ($(':checked[type=checkbox]').length != 0) {

            // get the obj id array
            $('table :checkbox[class!=select_all_checkbox]').each(function () {
                if (this.checked) {
                    objIdArray.push($(this).val());
                }
            });

            // choose the operation
            switch ($('.operationSlt').val()) {
                case 'delete':
                    deleteObj(objIdArray);
                    return;
                case 'recycle':
                    recycleObj(objIdArray);
                    return;
                default:
                    addErrorMessage("请选择批量操作内容！");
                    return;
            }
        } else {
            addErrorMessage("至少需要选中一行数据！");
            return;
        }
    }

});

function deleteObj(obj) {
    if (!confirm("一旦删除,不可恢复,确定?")) {
        return;
    }
    var objArray = new Array();
    if (obj instanceof Array) {
        objArray = obj;
        for (var i=0;i<objArray.length;i++) {
            $(' table tr[id=' + objArray[i] + ']').css('background-color', 'red');
        }
    } else {
        objArray.push(obj);
        $(' table tr[id=' + obj + ']').css('background-color', 'red');
    }

    $('.ajax_response').ajaxStart(function() {
        cleanErrorMessage();
        $('.applyBtn').attr('disabled', true);
    });

    $.ajax({
            url : 'wb_admin/deletePost',
            type : 'post',
            contentType : 'application/json',
            data : $.toJSON(objArray),
            dataType : 'json',
            success:function(data) {
                afterDelObj(data);
            },
            complete:function(jqXHR, textStatus) {
                $('.delete_term').attr('disabled', false);
            }
        });
}

function recycleObj(obj) {
    var objArray = new Array();
    if (obj instanceof Array) {
        objArray = obj;
        for (var i = 0; i < objArray.length; i++) {
            $(' table tr[id=' + objArray[i] + ']').css('background-color', 'red');
        }
    } else {
        objArray.push(obj);
        $(' table tr[id=' + obj + ']').css('background-color', 'red');

    }
    alert("recycleObj:" + objArray);
}

function updateObj(obj) {
    window.location.href = 'wb_admin/editPost?postId=' + obj;
}

function afterDelObj(data, textStatus) {
    if (data.jsonResult != null && data.jsonResult != undefined) {
        if (data.jsonResult.success) {
            addSuccessMessage(data.jsonResult.message);
        } else {
            addErrorMessage(data.jsonResult.message);
        }
        
        $.each(data.jsonResult.successList, function(key, value) {
            $(' table tr[id=' + value + ']').remove();
            $('#parent option[value=' + value + ']').remove();
        });

        if ($('table :checkbox[class=select_all_checkbox]').attr('checked') && data.jsonResult.successList.length != 0) {
            $('table :checkbox[class=select_all_checkbox]').removeAttr('checked');
        }
    } else {
        addErrorMessage("网络连接异常,请检查网络!");
    }
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