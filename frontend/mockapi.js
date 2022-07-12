const express = require("express");
const app = express();

console.log("Starting server and mock API...");

require('./main.js')

app.set('json spaces', 2);

app.get('/apps/Tommy', function(req, res){
    res.json({"1": [{1: "website 1", 2: "some sort of API", 3: "Just for example pretend this is important..."}], "2": [{1: "aaa", 2: "bbb", 3: "ccc"}]});
});

app.listen(8081, () => {
    console.log("Mock API is running @ http://localhost:8081");
})