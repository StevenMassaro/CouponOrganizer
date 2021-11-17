// promise.js
// Scripts useful for promise-related things.
// 2018-12-17 v1.0

function buildLoadPromise(requestType, url) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open(requestType, getApiBaseUrl(url));
        xhr.onload = () => resolve(xhr.responseText);
        xhr.onerror = () => reject(xhr.statusText);
        xhr.send();
    });
}