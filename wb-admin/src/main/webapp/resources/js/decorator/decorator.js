$(document).ready(function() {
	// for jquery.layout.ui
	$('body').layout({
			applyDefaultStyles:			true
		,	closable:					true	
		,	resizable:					true	 
		,	slidable:					true	
		,	livePaneResizing:			true
		,	north__slidable:			false	
		,	north__spacing_open:		0
		,	north__spacing_closed:		20		
		,	south__resizable:			false	
		,	south__spacing_open:		0		
		,	south__spacing_closed:		20		
		,	west__minSize:				100
		,	center__minWidth:			100
		,	west__animatePaneSizing:	false
		,	west__fxSpeed_size:			"fast"	
		,	west__fxSpeed_open:			1000	
		,	west__fxName_close:			"none"	
		,	west__showOverflowOnHover:	true
	});
	
	
	// for menu page
	$( "#accordion" ).accordion({
		active: 1,
		collapsible: true,
		autoHeight: false
	});
	
});