$(document).ready(function() {

    $(".nivel-texto").text( usuarioJson.actualExp + "/" + usuarioJson.totalExp);
    if (window.location.href.indexOf("save") > -1) {
        window.location.href = "home";
    }
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

    $(".modal-nivel").hide();

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
    $(".close-task").click(function(){
        $(".modal-tasks").close();
    })

    $(".close-habit").click(function(){
        $(".modal-habit").close();
    })

    $(".close-mission").click(function(){
        $(".modal-mission").close();
    })

    $(".close-nivel").click(function(){
        $(".modal-nivel").hide();
    })



    setInterval(() => {
        $(function() {
            if(usuarioJson.actualHealth >= 5){
                $("#vida-actual").addClass("transition");
                $(".vida-actual").width(usuarioJson.actualHealth + "%");
            }
            nivel();
        }, 600);
    })

    function nivel(){
        if((usuarioJson.actualExp * usuarioJson.totalExp / 100) < 5 && (usuarioJson.actualExp * usuarioJson.totalExp / 100) > 0){
            $("#nivel-actual").addClass("transition");
            $(".nivel-actual").width("4%");
        }
        if((usuarioJson.actualExp * usuarioJson.totalExp / 100) >= 5){
            $("#nivel-actual").addClass("transition");
            $(".nivel-actual").width(usuarioJson.actualExp * 100 / usuarioJson.totalExp + "%");
        }
    }

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
                    goalsJson.forEach(element => {
                        if(element.id == idGoal){
                            usuarioJson.actualExp = usuarioJson.actualExp + element.experience;
                            levelUp(usuarioJson.actualExp, usuarioJson.totalExp);
                            $(".nivel-texto").text( usuarioJson.actualExp + "/" + usuarioJson.totalExp);
                            $("#" + idGoal).addClass("task-finished")
                            var html = $("#" + idGoal).outerHTML();
                            $("."+ idGoal).remove();
                        }
                        if(element.type === "MISSION"){
                            $(".finished-mission").append(html);
                        }
                        else {
                            $(".finished-tasks").append(html);
                        }
                    })
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
                goalsJson.forEach(element => {
                    if(element.id == idGoal){
                        usuarioJson.actualExp = usuarioJson.actualExp + element.experience;
                        levelUp(usuarioJson.actualExp, usuarioJson.totalExp);
                        $(".nivel-texto").text( usuarioJson.actualExp + "/" + usuarioJson.totalExp);
                        $("#" + idGoal).addClass("task-finished")
                        var html = $("#" + idGoal).outerHTML();
                        $("."+ idGoal).remove();
                        $(".finished-habits").append(html);
                    }
                })
            }
            /*error: function(err){
            }*/
        });
    });

    jQuery.fn.outerHTML = function() {
        return jQuery('<div />').append(this.eq(0).clone()).html();
    };

    $(".finish").mouseover(function(){
        $(this).attr("src", "images/check.png");
    }).mouseout(function(){
        $(this).attr('src', "images/checkPurple.png");
    });

    $(".eliminate").mouseover(function(){
        $(this).attr("src", "images/trashRed.png");
    }).mouseout(function(){
        $(this).attr('src', "images/trash.png");
    });

    function levelUp(a, b) {
        if (a >= b) {
            $(".nivel-actual").animate({
                width: '100%',
            },0)
            setTimeout(function() {
                $(".modal-nivel").show()
                usuarioJson.actualExp = a - b
                usuarioJson.totalExp = Math.floor(b * 3 / 2)
                usuarioJson.level += 1
                $(".modal-nivel").show()
                $(".nivel-actual").animate({
                    width: usuarioJson.actualExp * 100 / usuarioJson.totalExp + '%',
                },0)
                $(".nivel-texto").text( usuarioJson.actualExp + "/" + usuarioJson.totalExp);
            }, 4000);

        }
    }
    $(document).on('click', '#imgPerfil', function() {
        $('#fotoPerfil').click();
    });

    $(document).on('change', '#fotoPerfil', function() {
        var formData = new FormData($('.cambiar-foto-perfil')[0]);
        $.ajax({
            url: 'cambiar-foto-perfil',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: (result) => {
                if (result) {
                    var url = URL.createObjectURL(this.files[0])
                    $('#imgPerfil').prop('src', url);
                } else {
                    alert('Error al cambiar la foto de perfil');
                }
            }
            /*error: function(err){
            }*/
        });
    });

});

