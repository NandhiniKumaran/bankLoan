jQuery(document).ready(

function($) {
loadPaymentList();
});
function loadPaymentList()
{

        $.ajax({
                 type: "GET",
                 url: "/api/loanPayment/retrievePaymentDetails/"+$("#loanId").val(),
                 success: function (data) {
                  for(let i=0;i< data.length;i++)
                  {
                     $('#payments').append('<tr><td>'+data[i].paymentNo+'</td><td>'+
                     data[i].paidAmount+'</td><td>'+
                     data[i].paidDate+'</td><td>'+
                     data[i].remarks+'</td><td>'+
                     '</td></tr>');
                     }
                 },
                 error: function (e) {

                 }
        });

}