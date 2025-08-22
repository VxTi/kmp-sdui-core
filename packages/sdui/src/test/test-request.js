


const url = "http://localhost:8080/initiation"
const headers = {
    'X-App-Locale': 'nl-NL',
    'X-App-Version': '1',
    'X-App-Identity': '123'
};

async function request() {

    await fetch(url, {
        method: 'GET',
        headers: headers
    }).then(res => {
        console.log(res);
        return res.text()
    }).then(res => {
        console.log(res);
    });
}

request();