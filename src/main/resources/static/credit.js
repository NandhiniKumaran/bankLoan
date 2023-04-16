jQuery(document).ready(

function($) {
loadCreditAcc();
});
function loadCreditAcc()
{
        $.ajax({
                 type: "GET",
                 url: "/api/accounts",
                 success: function (data) {
                  for(let i=0;i< data.length;i++)
                  {
                     $('#results').append('<tr><td>'+data[i].id+'</td><td>'+
                     data[i].user.username+'</td><td>'+
                     data[i].totalLimit+'</td><td>'+
                     data[i].currency+'</td><td>'+
                     data[i].startDate+'</td><td>'+
                     data[i].endDate+'</td><td>'+
                     data[i].totalLimitHome+'</td><td>'+
                     data[i].totalLimitCar+'</td><td>'+
                     '<a href=\"/loan/new/' +  data[i].id + '\"><input  class=\"btn btn-secondary\" type=\"submit\" value=\"Add New Loan\"></a>'
                     +'</td><td>'+
                     '<a href=\"/loan/retrieveLoan/' + data[i].id +'\"><input class=\"btn btn-secondary\" type=\"submit\" value=\"Loan list\"></a>'
                     +'</td></tr>');
                     }
                 },
                 error: function (e) {

                 }
        });

}