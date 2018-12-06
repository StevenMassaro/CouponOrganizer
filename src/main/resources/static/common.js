function buildLoadPromise (requestType, url){
    return new Promise((resolve, reject) => {
    	const xhr = new XMLHttpRequest();
    	xhr.open(requestType, getApiBaseUrl(url));
    	xhr.onload = () => resolve(xhr.responseText);
    	xhr.onerror = () => reject(xhr.statusText);
    	xhr.send();
    });
}

function getApiBaseUrl(url){
    let pathname = window.location.pathname;
    let baseName = "";
    if(pathname.includes("/")){
        const lastSlash = pathname.lastIndexOf("/");
        baseName = pathname.substring(0, lastSlash);
    }
    if(!url.startsWith("/")){
        url = "/" + url;
    }
    return window.location.origin + baseName + url;
}

function buildListEntry(value){
    return '<tr><td><a href=' + getApiBaseUrl("get") + '?id=' + value.id + '>' + value.store + '</a></td><td>' +
    	            value.deal + '</td><td>' +
    	            value.comment + '</td><td>' +
    	            (value.expirationDate ? value.expirationDate : "") + '</td><td>' +
    	            (value.dateDeleted ? value.dateDeleted : '<a href=' + getApiBaseUrl("setDateDeleted") + '?id=' + value.id +'>Mark deleted')
    	        '</td></tr>';
}

function runCouponsPromise(couponsPromise){
    couponsPromise.then((successMessage) => {
        $.each(JSON.parse(successMessage), function (key, value) {
            $('#coupons').append(
                buildListEntry(value)
            )});
    });
}