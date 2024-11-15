window.axios = window.axios.create({
    baseURL: '/studentSociety',
    timeout: 1000,
    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
});
window.axios.transformRequest= function (data) {
    let ret = ''
    for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
    }
    return ret
}