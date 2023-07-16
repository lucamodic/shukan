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

    if(!(Object.keys(errorTask).length === 0)){
        $(".modal-tasks").showModal();
    }

    if(!(Object.keys(errorMission).length === 0)){
        $(".modal-mission").showModal();
    }

    if(!(Object.keys(errorHabit).length === 0)){
        $(".modal-habit").showModal();
    }

    //opens tasks modal
    $(".add-task").click(function(){
        $(".modal-tasks").showModal();
    })

    $(".add-mission").click(function(){
        $(".modal-mission").showModal();
    })

    $(".add-habit").click(function(){
        $(".modal-habit").showModal();
    })

    //closes mdoal
    $(".close").click(function(){
        $("dialog").close();
    })

    setInterval(() => {
        $(function() {
            $(".vida-actual").width(vida + "%");
            if((exp * expTotal / 100) >= 5){
                $(".nivel-actual").width(exp * expTotal / 100 + "%");
            } else {
                $(".nivel-actual").width(0+"%");
            }
        }, 6000);
    })

    $(document).on('click', '.borrar', function() {
        if (confirm("Are you sure?")) {
            var idGoal = this.value;
            $.ajax({
                data: { id: idGoal },
                type: 'POST',
                url: `eliminar`,
                async: true,
                //contentType: false,
                //processData: false,
                /*/beforeSend: function() {
                },*/
                success: result => {
                    $("."+ idGoal).remove();
                }
                /*error: function(err){
                }*/
            });
        }
    });

    $(document).on('click', '.completar', function() {
            var idGoal = this.value;
            $.ajax({
                data: { id: idGoal },
                type: 'POST',
                url: `finish`,
                async: true,
                //contentType: false,
                //processData: false,
                /*/beforeSend: function() {
                },*/
                success: result => {
                    $("#" + idGoal).addClass("task-finished")
                    var html = $("#" + idGoal).outerHTML();
                    $("."+ idGoal).remove();
                    $(".finished").append(html);
                    //ANIMACION NIVEL
                }
                /*error: function(err){
                }*/
            });
    });

    $(document).on('click', '.completar-habit', function() {
        var idGoal = this.value;
        $.ajax({
            data: { id: idGoal },
            type: 'POST',
            url: `finish`,
            async: true,
            //contentType: false,
            //processData: false,
            /*/beforeSend: function() {
            },*/
            success: result => {
                $("#" + idGoal).addClass("task-finished")
                var html = $("#" + idGoal).outerHTML();
                $('.' + idGoal).remove();
                $(".habits-finished").append(html);
            }
            /*error: function(err){
            }*/
        });
    });

    jQuery.fn.outerHTML = function() {
        return jQuery('<div />').append(this.eq(0).clone()).html();
    };

});
