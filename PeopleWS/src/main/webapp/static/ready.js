
function readyFuncs(){
    getAjax();
    $(document).ready(function(){
        $("#testButton").click( function()
            {
                postAjax();
            }
        );
    });
}

function getAjax() {

    var $table = $('<table/>');

    $.ajax({
        url: "/invoice.app",
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function(data) {
            $.each(data, function(index) {
                if (data[index].customer != null){
                    alert(data[index].customer.surName);
                }
                else {
                    $table.append( '<tr><td>' + data[index].id + '</td><td>' + data[index].amount + '</td><td>' + data[index].customerReference + '</td></tr>' );
                }
            });
            $table.append( '</table>' );

            $('#holder').append($table);
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}

function postAjax() {

    var name = $("#name").val();
    var email = $('#email').val();
    var password = $('#password').val();
    var json = { "name" : name, "email" : email, "password": password};

    alert("jvhgvhg");

    alert($("#name").val());
    alert($("#email").val());

    $.ajax({
        url: "/invoicePost.app",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(json),
        contentType: 'application/json',
        mimeType: 'application/json'

    });
}


//function getFormData($form){
//    var unindexed_array = $form.serializeArray();
//    var indexed_array = {};
//
//    $.map(unindexed_array, function(n, i){
//        indexed_array[n['name']] = n['value'];
//    });
//
//    return indexed_array;
//}