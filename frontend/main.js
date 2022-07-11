global.__basedir = __dirname;
global.__scriptsDir = __dirname + '/scripts';
global.__javaDir = __dirname + '/java';

const port = 8080;

// import modules
var colors = require('colors');
const express = require("express");
const app = express();
// import in-project dependencies
const initRoutes = require("./scripts/router");

// Exit if there is no configuration
if (port == undefined) {
    console.log("X ".brightRed.bold+"Port not found, likely because .env may not exist! Please run the command ".red+"mv .env.example .env && nano .env".brightRed.bgGray+" to create .env and edit it!".red);
    // TODO : Add a configuration script
    // console.log("X ".brightRed.bold+"Port not found, likely because .env may not exist! Please run with the ".red+"--configure".brightRed.bgGray+" flag to generate it!".red);
    process.exit()
}

app.use(express.urlencoded({ extended: true }));
// Create static route for home page, assets, etc.
app.use(express.static('content/static'));
// Initialize routes.
initRoutes(app);
app.set('view engine', 'ejs');

app.listen(port, () => {
    console.log(`Webserver is running on port ${port}`);
    console.log("DEBUG- The server is running @ http://localhost:" + port);
})