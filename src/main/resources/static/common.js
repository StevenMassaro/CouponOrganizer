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