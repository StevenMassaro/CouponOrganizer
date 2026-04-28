function couponAction(url, successMsg) {
    fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            alert(data.message);
            location.reload();
        })
        .catch(function(err) {
            alert('Action failed. Please try again.');
        });
}

function runCouponsPromise(couponsPromise, showRestore) {
    couponsPromise.then((successMessage) => {
        $('#coupons').DataTable({
            data: JSON.parse(successMessage),
            "columns": [{
                    "data": "store",
                    "render": function(data, type, row) {
                        return row.fileExists ?
                            '<a href=' + getApiBaseUrl("get") + '?id=' + row.id + '>' + row.store + '</a>' :
                            row.store;
                    }
                },
                {
                    "data": "deal"
                },
                {
                    "data": "comment"
                },
                {
                    "data": "expirationDate"
                },
                {
                    "data": null,
                    "defaultContent": "",
                    "render": function(data, type, row, meta) {
                        return (row.dateDeleted ? row.dateDeleted : '<a href="#" onclick="event.preventDefault(); couponAction(\'' + getApiBaseUrl("setDateDeleted") + '?id=' + row.id + '\')">Delete</a>');
                    }
                },
                {
                    "data": null,
                    "defaultContent": "",
                    "render": function(data, type, row, meta) {
                        return (!row.dateDeleted ? '<a href="edit.html?id=' + row.id + '">Edit</a>' : '');
                    }
                },
                ...(showRestore ? [{
                    "data": null,
                    "defaultContent": "",
                    "render": function(data, type, row, meta) {
                        return '<a href="#" onclick="event.preventDefault(); couponAction(\'' + getApiBaseUrl("restore") + '?id=' + row.id + '\')">Restore</a>';
                    }
                }] : [])
            ],
            responsive: true,
            "pageLength": 50
        });
    });
}