jQuery(document).ready(

function($) {
loadLoanList();
});
function loadLoanList()
{

        $.ajax({
                 type: "GET",
                 url: "/api/loan/retrieveLoan/"+$("#accountNo").val(),
                 success: function (data) {
                  for(let i=0;i< data.length;i++)
                  {

                  if( data[i].paymentStatus == null)
                  {
                  data[i].paymentStatus='';
                  }
                     $('#loans').append('<tr><td>'+data[i].loanId+'</td><td>'+
                     data[i].loanType+'</td><td>'+
                     data[i].amount+'</td><td>'+
                     data[i].currency+'</td><td>'+
                     data[i].startDate+'</td><td>'+
                     data[i].endDate+'</td><td>'+
                     data[i].interest+'</td><td>'+
                     data[i].paymentStatus+'</td><td>'+
                     '<a href=\"/loanPayment/new/' +  data[i].loanId + '\"><input  type=\"submit\" value=\"Pay Loan\"></a>'
                     +'</td><td>'+
                     '<a href=\"/loanPayment/retrievePaymentDetails/' + data[i].loanId +'\"><input  type=\"submit\" value=\"Payment List\"></a>'
                     +'</td></tr>');
                     }
                 },
                 error: function (e) {

                 }
        });

}