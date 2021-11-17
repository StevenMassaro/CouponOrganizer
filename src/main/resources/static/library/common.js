// common.js
// A JavaScript library containing commonly used and useful scripts.
// 2018-12-17 v1.0

function getApiBaseUrl(url) {
    let pathname = window.location.pathname;
    let baseName = "";
    if (pathname.includes("/")) {
        const lastSlash = pathname.lastIndexOf("/");
        baseName = pathname.substring(0, lastSlash);
    }
    if (!url.startsWith("/")) {
        url = "/" + url;
    }
    return window.location.origin + baseName + url;
}