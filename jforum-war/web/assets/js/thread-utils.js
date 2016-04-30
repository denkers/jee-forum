$(function()
{
    $('#post-area').val('');
    $('.post-reply-button').click(function(e)
    {
        var id          =   $(this).closest(".post-section").attr("id");
        var inject      =   "<REPLYTO>" + id + "</REPLYTO>";   
        $('#post-area').val($('#post-area').val() + inject);
        location.hash   =   'reply';
    });
});