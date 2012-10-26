$(document).ready(function() {
    $("#menu_div>ul>li").bind('mouseover',
        function() {
            $(this).children('ul').slideDown('fast');
        }).bind('mouseleave', function() {
            $(this).children('ul').slideUp('fast');
        });
}); 