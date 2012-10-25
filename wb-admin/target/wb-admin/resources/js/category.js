
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


    
    // Add term event
    $('#add_term').click(addTermHandler);
    function addTermHandler() {
        var category = $('form#category').serializeObject();
        var categoryJSON = $.toJSON(category);

        // before ajax
        $('#ajax_response').ajaxStart(function() {
            cleanErrorMessage();
            $('#add_term').attr('disabled', true);
        });

        // start ajax
        $.ajax({
            url : 'wb_admin/addCategory',
            type : 'post',
            contentType : 'application/json',
            data : categoryJSON,
            dataType : 'json',
            success:function(data, textStatus, jqXHR) {
                afteAddCategory(data, category,textStatus, jqXHR);
            },
            error : function(jqXHR, textStatus, errorThrown) {
                afterAddCategoryError(jqXHR, textStatus, errorThrown);
            },
            complete:function(jqXHR, textStatus) {
                $('#add_term').attr('disabled', false);
            }
        });
    }
    function afterAddCategoryError(jqXHR, textStatus, errorThrown){
        alert(textStatus);
    }
     function afteAddCategory(data, category,textStatus, jqXHR) {
            if (data.CategoryID != undefined) {
                var rowClass = $('tbody tr').length % 2 == 0 ? 'odd' : 'even';
                var tr = "<tr id='" + data.CategoryID + "' class='" + rowClass + "'><td colspan='5' style='padding: 0;'>" +
                    "<ul style='display: none;'>" +
                    "<li class='check-column'><input type='checkbox' style='margin-top:15px;'></li>" +
                    "<li class='name column-name'>" + category.name + "</li>" +
                    "<li class='description column-description'>" + category.description + "</li>" +
                    "<li class='slug column-slug'>" + category.slug + "</li>" +
                    "<li class='count column-count'>0</li>" +
                    "</ul></td></tr>";
                var ntr = "<th class='check-column'><input type='checkbox' value='" + data.CategoryID + "'></th>" +
                        "<td class='name column-name'>" + category.name + "</td>" +
                        "<td class='description column-description'>" + category.description + "</td>" +
                        "<td class='slug column-slug'>" + category.slug + "</td>" +
                        "<td class='count column-count'>0</td>";

                $('tbody').append(tr);
                // set ul height and width
                $('ul').height($('tbody tr:first').height());
                $('li').each(function() {
                    $(this).width($("tbody tr:first td[class='" + $(this).attr('class') + "']").width());
                    $(this).height($("tbody tr:first td[class='" + $(this).attr('class') + "']").height());

                    $(this).width($("tbody tr:first th[class='" + $(this).attr('class') + "']").width());
                    $(this).height($("tbody tr:first th[class='" + $(this).attr('class') + "']").height());
                });
                //replace ui with new tr
                $('ul').slideDown('slow', function() {
                    $('td[colspan=5]').remove();
                    $('tr:last').append(ntr);
                });

                // reset form
                $('form#category').each(function() {
                    this.reset();
                });
//                addSuccessMessage("新的分类目录增加成功!");
            } else if (data.Message != undefined) {
                addErrorMessage(data.Message);
            } else {
                addErrorMessage("网络连接异常,请检查网络!");
            }
        }
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
    
    // Delete term event
    $('#delete_term').click(function() {
        if (confirm("一旦删除,不可恢复,确定?")) {
            var termIds = new Array();
            if ($('#operation').val() == 'delete') {
                if ($(':checked[type=checkbox]').length != 0) {

                    $('table :checkbox[class!=select_all_checkbox]').each(function () {
                        if (this.checked) {
                            termIds.push($(this).val());
                            if (this.value != 2) {
                                //$(' table tr[id=' + this.value + ']').fadeOut('slow');
                                $(' table tr[id=' + this.value + ']').css('background-color','red');
                            }
                        }
                    });

                    // before ajax
                    $('#ajax_response').ajaxStart(function() {
                        cleanErrorMessage();
                        $('#delete_term').attr('disabled', true);
                    });

                    $.ajax({
                        url : 'wb_admin/deleteCategory',
                        type : 'post',
                        contentType : 'application/json',
                        data : termIds.toString(),
                        dataType : 'json',
                        success:function(data) {
                            afterDelCategory(data);
                        },
                        complete:function(jqXHR, textStatus) {
                            $('#delete_term').attr('disabled', false);
                        }
                    });
                } else {
                    addErrorMessage("至少需要选中一行数据！");
                }
            } else {
                addErrorMessage("请选择批量操作内容！");
            }
        }

        function afterDelCategory(data) {
            if (data.CategoryID != undefined) {
                for (var i = 0; i < data.CategoryID.length; i++) {
                    $(' table tr[id=' + data.CategoryID[i] + ']').fadeOut(1000);
                    $(' table tr[id=' + data.CategoryID[i] + ']').remove();
                }
//                addSuccessMessage("分类目录删除成功!");
            } else if (data.Message != undefined) {
                addErrorMessage(data.Message);
            } else {
                addErrorMessage("网络连接异常,请检查网络!");
            }
        }
    });
});

function cleanErrorMessage(){
    $('#ajax_response').html("<div class='error'><br></div>");
}
function addErrorMessage(msg){
     $('#ajax_response').html("<div class='error'>" + msg + "</div>");
}
function addSuccessMessage(msg){
     $('#ajax_response').html("<div class='success'>" + msg + "</div>");
}