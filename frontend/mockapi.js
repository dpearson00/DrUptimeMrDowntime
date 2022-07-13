const express = require("express");
const app = express();

app.set('json spaces', 2);

app.get('users/auth', function(req, res) {
    if (req.query.username == "Tommy" && req.query.password == "test") {res.json({"1": 1});}
    else if (req.query.username == "Tyler" && req.query.password == "test") {res.json({"1": 1});}
    else if (req.query.username == "Daniel" && req.query.password == "test") {res.json({"1": 1});}
    else {res.json({"1": 0});}
});

app.get('/users/apps/Tyler', function(req, res){
    res.json({"1": ["website 0", "no"], "2": ["ccc"]});
});

app.get('/users/apps/Daniel', function(req, res){
    res.json({"1": ["website 0", "no"], "2": ["abc", "def"]});
});

app.get('/users/apps/Tommy', function(req, res){
    res.json({1: ["website 1","some sort of API","Just for example pretend this is important..."], "2": ["aaa","bbb"]});
});

app.get('/apps/aaa', function(req, res){
    res.json({1: "website 1", 2: "This website is pretty neat, but api 1 is cooler", 3: "Tommy"});
});

app.get('/apps/bbb', function(req, res){
    res.json({1: "Number one API EVER", 2: "good apis are cool", 3: "Tommy"});
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