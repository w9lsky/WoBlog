$(document).ready(function() {

//    $('#add_term').click(function(){
//        $('form #category').submit();
//    });
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

  // Delete term event
    $('.apply_btn').click(deleteTerm);
});

function updateTerm(termId) {
    $(':hidden[id=id]').val(termId);
    $(':text[id=name]').val($('tr[id=' + termId + '] a[id=name]').html());
    $(':text[id=slug]').val($('tr[id=' + termId + '] .slug').html());
    $('option[value=' + $(':hidden[id=' + termId + ']').val() + ']').attr('selected','true');
    $(':textarea[id=description]').val($('tr[id=' + termId + '] .description').html());
//    $(':select[id=parent]').attr('readonly','true');
}

function deleteTerm(termId){
    var termIds = new Array();
    if (!isNaN(termId)) {
        if (!confirm("一旦删除,不可恢复,确定?")) {
            return;
        }
        termIds.push(termId);
        $(' table tr[id=' + termId + ']').css('background-color', 'red');
    } else {
        if ($('.operation').val() == 'delete') {
            if ($(':checked[type=checkbox]').length != 0) {
            } else {
                addErrorMessage("至少需要选中一行数据！");
                return;
            }
        } else {
            addErrorMessage("请选择批量操作内容！");
            return;
        }
        if (!confirm("一旦删除,不可恢复,确定?")) {
            return;
        }
        $('table :checkbox[class!=select_all_checkbox]').each(function () {
            if (this.checked) {
                termIds.push($(this).val());
                $(' table tr[id=' + this.value + ']').css('background-color', 'red');
            }
        });
    }

    // before ajax
        $('#ajax_response').ajaxStart(function() {
            cleanErrorMessage();
            $('.apply_btn').attr('disabled', true);
        });
        var category = $('.apply_btn').attr('name');
        $.ajax({
            url : 'wb_admin/deleteTerm?taxonomy=' + category,
            type : 'post',
            contentType : 'application/json',
            data : $.toJSON(termIds),
            dataType : 'json',
            success:function(data) {
                afterDelCategory(data);
            },
            complete:function(jqXHR, textStatus) {
                $('.apply_btn').attr('disabled', false);
            }
        });



    function afterDelCategory(data) {
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