jQuery(document).ready(

function($) {

    $("#paymentSubmit").click(function(event) {
        $("#errorMsg").html("").removeClass("alert alert-danger");
        $("#successMsg").html("").removeClass("alert alert-success");
        var data = {};
        data["paidAmount"] = parseFloat($("#paidAmount").val());
        data["paidDate"] = $("#paidDate").val();
        data["remarks"] = $("#remarks").val();
        data["endDate"] = $("#endDate").val();

        var loan = {};
        loan["loanId"] = parseFloat($("#loanId").val());
        data["loan"] =loan;


        $("#paymentSubmit").prop("disabled", true);

        $.ajax({
                 type: "POST",
                 contentType: "application/json",
                 url: "/api/loanPayment/new",
                 data: JSON.stringify(data),
                 dataType: 'json',
                 success: function (data, textStatus, jqXHR) {
                  $("#successMsg").html(jqXHR.getResponseHeader('successMessage')).addClass("alert alert-success");
                    setTimeout(function() {
                                             window.location.href = "/loanPayment/retrievePaymentDetails/"+data.loan.loanId;
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
                        }
                       else
                       {
                       errorText+="Error occurred in the system"
                       }
                        $("#errorMsg").html(errorText).addClass("alert alert-danger");
                                $("#paymentSubmit").prop("disabled", false);

                 }
        });


    });



}


);