


const url = "http://localhost:8080"
const headers = {
    'X-App-Locale': 'nl-NL',
    'X-App-Version': '1',
    'X-App-Identity': '  nl_NL123'
};

async function request() {
    await fetch(url, {
        method: 'GET',
        headers: headers
    }).then(res => res.json()).then(res => {
        console.log(JSON.stringify(res, null, 2));
    });
}

request();