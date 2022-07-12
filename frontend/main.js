global.__basedir = __dirname;
global.__scriptsDir = __dirname + '/scripts';

require('dotenv').config()

// import modules
var colors = require('colors');
const express = require("express");
const app = express();
const path = require('path');
var session = require('express-session');
// import in-project dependencies
const initRoutes = require("./scripts/router");

// Check .env to see whether or not to use the mock API
if (process.env.mockapi == "true") { global.__apiLink = `http://localhost:8081`; console.log("Using the mock API."); require('./mockapi.js')} else { global.__apiLink = process.env.prodApiLink; console.log("Using PROD API, currently set to " + process.env.prodApiLink)}

// Exit if critical config variables are not set
if (process.env.port == "" || process.env.port == undefined || process.env.prodApiLink == "" || process.env.prodApiLink == undefined) {
    console.log("X ".brightRed.bold+"Critical elements missing from process! This is likely because .env may not exist! Please run the command ".red+"mv .env.example .env && nano .env".brightRed.bgGray+" to create .env and edit it!".red);
    process.exit()
}

app.use(session({
    resave: false,
    saveUninitialized: false,
    secret: 'superfuntest', // session secret, combined with session ID to compute hash
  }));

app.use(express.urlencoded({ extended: true }));
// Create static route for home page, assets, etc.
app.use('/static', express.static(path.join(__dirname, 'views/static')));
// Initialize routes.
initRoutes(app);
app.set('view engine', 'ejs');

app.listen(process.env.port, () => {
    console.log(`Webserver is running @ http://localhost:${process.env.port}`);
})