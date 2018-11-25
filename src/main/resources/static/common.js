function buildLoadPromise (requestType, url){
    return new Promise((resolve, reject) => {
    	const xhr = new XMLHttpRequest();
    	xhr.open(requestType, window.location.origin + url);
//    	xhr.open("GET", window.location.origin + "/list");
    	xhr.onload = () => resolve(xhr.responseText);
    	xhr.onerror = () => reject(xhr.statusText);
    	xhr.send();
    });
}