jQuery(document).ready(

function($) {

    $("#registerbtn").click(function(event) {
        $("#errorMsg").html("").removeClass("alert alert-danger");
        $("#successMsg").html("").removeClass("alert alert-success");
        var data = {};
        data["firstName"] = $("#firstName").val();
        data["lastName"] = $("#lastName").val();
        data["email"] = $("#email").val();
        data["password"] = $("#password").val();
        data["role"] = $("#role").val();


        $("#registerbtn").prop("disabled", true);

        $.ajax({
                 type: "POST",
                 contentType: "application/json",
                 url: "/api/register/save",
                 data: JSON.stringify(data),
                 dataType: 'json',
                 success: function (data, textStatus, jqXHR) {
                  $("#successMsg").html(jqXHR.getResponseHeader('successMessage')).addClass("alert alert-success");
                  $("#registerbtn").prop("disabled", false);
               setTimeout(function() {
                                         window.location.href = "/login";
                                       }, 5000); // 5000 milliseconds (5 seconds) delay


                 },
                 error: function (e) {
                 let errorText="";
                        if(e.status == 409)
                        {
                        errorText+=e.getResponseHeader('failureMessage');
                        }
                         else if(e.responseText != '')
                         {
                        let obj=jQuery.parseJSON(e.responseText);
                        errorText="<ul>";
                        for(let i=0;i< obj.errors.length;i++)
                        {
                        errorText+="<li>"+obj.errors[i]+"</li>";
                        }
                        errorText+="</ul>"
                        }
                        else
                        {
                        errorText+="Error occurred in the system"
                        }
                        $("#errorMsg").html(errorText).addClass("alert alert-danger");
                        $("#registerbtn").prop("disabled", false);

                 }
        });


    });



}


);