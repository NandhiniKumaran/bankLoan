jQuery(document).ready(

function($) {

    $("#loanSubmit").click(function(event) {
        $("#errorMsg").html("").removeClass("alert alert-danger");
        $("#successMsg").html("").removeClass("alert alert-success");
        var data = {};
        data["loanType"] = $("#loanType").val();
        data["amount"] = parseFloat($("#amount").val());
        data["interest"] = parseFloat($("#interest").val());
        data["currency"] = $("#currency").val();
        data["startDate"] = $("#startDate").val();
        data["endDate"] = $("#endDate").val();
        data["loanType"] = $("#loanType").val();

        var credit = {};
        credit["id"] = parseFloat($("#credit").val());
        data["credit"] =credit;


        $("#loanSubmit").prop("disabled", true);

        $.ajax({
                 type: "POST",
                 contentType: "application/json",
                 url: "/api/loan/new",
                 data: JSON.stringify(data),
                 dataType: 'json',
                 success: function (data, textStatus, jqXHR) {
                  $("#successMsg").html(jqXHR.getResponseHeader('successMessage')).addClass("alert alert-success");

                      setTimeout(function() {
                           window.location.href = "/loan/retrieveLoan/"+data.credit.id;
                         }, 5000); // 5000 milliseconds (5 seconds) delay
                  },
                 error: function (e) {
                   let errorText="";
                     if(e.status == 500)
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
                        }else
                         {
                         errorText+="Error occurred in the system"
                         }
                        $("#errorMsg").html(errorText).addClass("alert alert-danger");
                                $("#loanSubmit").prop("disabled", false);


                 }
        });


    });



}


);