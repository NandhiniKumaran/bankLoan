jQuery(document).ready(

function($) {

    $("#submitbtn").click(function(event) {
        $("#errorMsg").html("").removeClass("alert alert-danger");
        $("#successMsg").html("").removeClass("alert alert-success");
        var data = {};
        data["totalLimit"] = parseFloat($("#totalLimit").val());
        data["currency"] = $("#currency").val();
        data["startDate"] = $("#startDate").val();
        data["endDate"] = $("#endDate").val();
        data["totalLimitHome"] = parseFloat($("#totalLimitHome").val());
        data["totalLimitCar"] = parseFloat($("#totalLimitCar").val());

        var user = {};
        user["firstName"] = $("#firstName").val();
        user["lastName"] = $("#lastName").val();
        user["email"] = $("#email").val();

        data["user"] = user;
        $("#submitbtn").prop("disabled", true);

        $.ajax({
                 type: "POST",
                 contentType: "application/json",
                 url: "/api/accounts",
                 data: JSON.stringify(data),
                 dataType: 'json',
                 success: function (data, textStatus, jqXHR) {
                  $("#successMsg").html(jqXHR.getResponseHeader('successMessage')).addClass("alert alert-success");

                      setTimeout(function() {
                           window.location.href = "/accounts";
                         }, 5000); // 5000 milliseconds (5 seconds) delay

                 },
                 error: function (e) {
                        let errorText="";
                          if(e.status == 409)
                          {
                          errorText=e.getResponseHeader('failureMessage');
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
                                $("#submitbtn").prop("disabled", false);

                 }
        });


    });



}


);