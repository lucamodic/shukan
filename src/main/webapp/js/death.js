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

    if(!(Object.keys(errorDeath).length === 0)){
        $(".modal-death").show();
        $(".modal-death").addClass("transitionModal");
    }
});