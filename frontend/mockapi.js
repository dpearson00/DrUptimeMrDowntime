const express = require("express");
const app = express();

app.set("json spaces", 2);
app.use(express.json());

app.post("/users/auth", function (req, res) {
  if (req.body.email == "Tommy" && req.body.password == "test") {
    res.json({ 1: "SUCCESS" });
  } else if (req.body.email == "Tyler" && req.body.password == "test") {
    res.json({ 1: "SUCCESS" });
  } else if (req.body.email == "Daniel" && req.body.password == "test") {
    res.json({ 1: "SUCCESS" });
  } else {
    res.json({ 1: "AUTH-FAILED" });
  }
});
/*
POST: /users/auth
username: String
password: String

IF login successful:
{"1": "SUCCESS"}
ELSE:
{"1": "AUTH-FAILED"}
*/

app.get("/users/:name/apps", function (req, res) {
  if (req.params.name == "Tyler") {
    res.json({ 1: ["another website...", "The Best API"], 2: ["ccc", "bbb"] });
  } else if (req.params.name == "Daniel") {
    res.json({ 1: ["The Zeroth Site", "The Danny API"], 2: ["abc", "def"] });
  } else if (req.params.name == "Tommy") {
    res.json({ 1: ["website 1"], 2: ["aaa"] });
  } else {
    res.json({ 1: "UNKNOWN" });
  }
});
/*
GET: /users/apps/{name}
IF name is valid:
{1: ["{site1}", "{site2}", "{etc}"], 2: [{id1}, {id2}, {etc}]}
ELSE:
{"1": "UNKNOWN"}
*/

app.get("/apps/:user/view/:id", function (req, res) {
  if (req.params.id == "aaa") {
    res.json({
      1: "website 1",
      2: "This website is pretty neat, but the api is cooler...",
      3: "Tommy",
    });
  } else if (req.params.id == "bbb") {
    res.json({ 1: "The Best API", 2: "good apis are cool", 3: "Tyler" });
  } else if (req.params.id == "ccc") {
    res.json({
      1: "another website...",
      2: "This website is pretty neat, but the api is cooler...",
      3: "Tyler",
    });
  } else if (req.params.id == "abc") {
    res.json({ 1: "The Zeroth Site", 2: "good apis are cool", 3: "Daniel" });
  } else if (req.params.id == "def") {
    res.json({
      1: "The Danny API",
      2: "It's an S3 bucket interface that's fun to test!",
      3: "Daniel",
    });
  } else {
    res.json({ 1: "UNKNOWN" });
  }
});
/*
GET: /apps/{id}
IF id is valid:
{1: "{appName}", 2: "{appDescription}", 3: "{appOwner}"}
ELSE:
{"1": "UNKNOWN"}
*/

app.listen(8081, () => {
  console.log("Mock API is running @ http://localhost:8081");
});
