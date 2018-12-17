function runCouponsPromise(couponsPromise){
    couponsPromise.then((successMessage) => {
        $.each(JSON.parse(successMessage), function (key, value) {
            $('#coupons').append(
                buildListEntry(value)
            )});
    });
}

function buildListEntry(value){
    return '<tr><td><a href=' + getApiBaseUrl("get") + '?id=' + value.id + '>' + value.store + '</a></td><td>' +
    	            value.deal + '</td><td>' +
    	            value.comment + '</td><td>' +
    	            (value.expirationDate ? value.expirationDate : "") + '</td><td>' +
    	            (value.dateDeleted ? value.dateDeleted : '<a href=' + getApiBaseUrl("setDateDeleted") + '?id=' + value.id +'>Mark deleted')
    	        '</td></tr>';
}