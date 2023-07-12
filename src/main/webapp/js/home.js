$(document).ready(function() {

    //allows showModal()
    $.fn.showModal = function() {
        el = $(this);
        if (el.is('dialog')) {
            el[0].showModal();
        }
        return el;
    };

    //allows close()
    $.fn.close = function() {
        el = $(this);
        if (el.is('dialog')) {
            el[0].close();
        }
        return el;
    };

    //opens tasks modal
    $(".add-task").click(function(){
        $(".modal-tasks").showModal();
    })

    //closes mdoal
    $(".close").click(function(){
        $("dialog").close();
    })

});
