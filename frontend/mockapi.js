const express = require("express");
const app = express();

console.log("Starting server and mock API...");

require('./main.js')

app.set('json spaces', 2);

app.get('/apps/*', function(req, res){
    res.json({"apps": [{"Website 1": "This website is a website and it's not cool and not an actual website, just test data.", "Another WEBSITE??": "This website is a website and it's not cool and not an actual website, just test data.", "Cool API": "again, it's not real...s just test data."}]});
});

app.listen(8081, () => {
    console.log("Mock API is running @ http://localhost:8081");
})