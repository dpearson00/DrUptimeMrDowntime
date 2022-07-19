var colors = require("colors");

// Exit if critical config variables are not set
if (
  process.env.port == "" ||
  process.env.port == undefined ||
  process.env.prodApiLink == "" ||
  process.env.prodApiLink == undefined
) {
  console.log(
    "X ".brightRed.bold +
      "Critical elements missing from process! This is likely because .env may not exist! Please run the command "
        .red +
      "mv .env.example .env && nano .env".brightRed.bgGray +
      " to create .env and edit it!".red
  );
  process.exit();
}

// ** .env paramaters **
// Use mock API
if (process.env.mockApi == "true") {
  global.__apiLink = `http://localhost:8081`;
  console.log("Using the mock API.");
  require(`${__basedir}/mockapi.js`);
} else {
  global.__apiLink = process.env.prodApiLink;
  console.log("Using PROD API, currently set to " + process.env.prodApiLink);
}
