const express = require("express");
const app = express();

app.set('json spaces', 2);
app.use(express.json());

app.post('/users/auth', function(req, res) {
    if (req.body.username == "Tommy" && req.body.password == "test") {res.json({"1": "SUCCESS"});}
    else if (req.body.username == "Tyler" && req.body.password == "test") {res.json({"1": "SUCCESS"});}
    else if (req.body.username == "Daniel" && req.body.password == "test") {res.json({"1": "SUCCESS"});}
    else {res.json({"1": "AUTH-FAILED"});}
});

app.get('/users/apps/Tyler', function(req, res){
    res.json({1: ["another website...", "The Best API"], 2: ["ccc", "bbb"]});
});

app.get('/users/apps/Daniel', function(req, res){
    res.json({1: ["The Zeroth Site", "The Danny API"], 2: ["abc", "def"]});
});

app.get('/users/apps/Tommy', function(req, res){
    res.json({1: ["website 1"], 2: ["aaa"]});
});

app.get('/apps/aaa', function(req, res){
    res.json({1: "website 1", 2: "This website is pretty neat, but the api is cooler...", 3: "Tommy"});
});

app.get('/apps/bbb', function(req, res){
    res.json({1: "The Best API", 2: "good apis are cool", 3: "Tyler"});
});

app.get('/apps/ccc', function(req, res){
    res.json({1: "another website...", 2: "Just for example pretend this is important...", 3: "Tyler"});
});

app.get('/apps/abc', function(req, res){
    res.json({1: "The Zeroth Site", 2: "this website brings us closer to greatness", 3: "Daniel"});
});

app.get('/apps/def', function(req, res){
    res.json({1: "The Danny API", 2: "It's fun to test, trust me.", 3: "Daniel"});
});

app.listen(8081, () => {
    console.log("Mock API is running @ http://localhost:8081");
})