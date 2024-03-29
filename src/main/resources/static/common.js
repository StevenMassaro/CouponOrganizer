function runCouponsPromise(couponsPromise){
    couponsPromise.then((successMessage) => {
        $('#coupons').DataTable({
            data: JSON.parse(successMessage),
            "columns": [
                {
                    "data": "store",
                    "render": function (data, type, row) {
                        return row.fileExists ?
                            '<a href=' + getApiBaseUrl("get") + '?id=' + row.id + '>' + row.store + '</a>' :
                            row.store;
                    }
                },
                {"data": "deal"},
                {"data": "comment"},
                {"data": "expirationDate"},
                {
                    "data": null,
                    "defaultContent": "",
                    "render": function(data,type,row,meta) {
                        return (row.dateDeleted ? row.dateDeleted : '<a href=' + getApiBaseUrl("setDateDeleted") + '?id=' + row.id +'>Delete');
                    }
                }
            ],
            responsive: true,
            "pageLength": 50
        });
    });
}