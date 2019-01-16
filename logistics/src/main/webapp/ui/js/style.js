/**
 * Created by MaggieLu on 2017/6/1.
 */
$(function () {
    resize();
});

$(window).resize(function(){
    resize();
});

function resize(){
    $(".main-chart").outerHeight($(window).outerHeight()-70);
    $(".main").outerHeight($(window).outerHeight()-70);
    $(".alert-content").css("marginLeft",($(".alert-box").outerWidth()-$(".alert-content").outerWidth())/2);
    //$(".alert-content").css("marginTop",($(".alert-box").outerHeight()-$(".alert-content").outerHeight())/2);
}

function showBox(){
    $(".alert-box").show();
    $(".alert-content").css("marginTop",($(".alert-box").outerHeight()-$(".alert-content").outerHeight())/2);
}

function hideBox(){
    $(".alert-box").hide();
}