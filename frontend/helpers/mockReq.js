require("dotenv").config(); // Use dotenv to load .env file
const { default: axios } = require("axios");

/*
  Auth route
  /users/auth
*/
const auth = (req, res) => {
  axios
    .post(`${process.env.prodApiLink}/users/auth`, {
      email: "test@test.com",
      password: "test",
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        console.log(
          "/users/auth: Test success!\n Authenticated as " + req.body.token
        );
      } else {
        console.log("/users/auth: Test failed!");
      }
    })
    .catch(function (error) {
      console.log("/users/auth: Test failed!");
    });
};

/*
  Create app route
  /apps/:userId/create
*/
const createApp = (req, res) => {
  axios
    .post(`${process.env.prodApiLink}/apps/000000/create`, {
      name: "Test App",
      description: "Test App Description",
      url: "https://test.com",
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        console.log("/apps/:userId/create: Test success!");
      } else {
        console.log("/apps/:userId/create: Test failed!");
      }
    })
    .catch(function (error) {
      console.log("/apps/:userId/create: Test failed!");
    });
};

/*
  Create user route
  /users/create
*/
const createUser = (req, res) => {
  axios
    .post(`${process.env.prodApiLink}/users/create`, {
      name: "Create-User Test-User",
      password: "createtestuser",
      email: "createtest@test.com",
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        console.log("/users/create: Test success!");
      } else {
        console.log("/users/create: Test failed!");
      }
    })
    .catch(function (error) {
      console.log("/users/create: Test failed!");
    });
};

/*
    Get a user's apps
    /apps/:userId
*/
const getApps = (req, res) => {
  axios
    .get(`${process.env.prodApiLink}/users/000000/apps`)
    .then(function (response) {
      var name = JSON.parse(JSON.stringify(response.data[1]));
      var ids = JSON.parse(JSON.stringify(response.data[2]));
      console.log(
        "/users/:userId/apps: Test success! Apps are:" +
          name +
          " with ids: " +
          ids
      );
    })
    .catch(function (error) {
      console.log("/users/:userId/apps: Test failed!");
    });
};

/*
    Get app details
    /apps/:userId/view/:appId
*/
const getApp = (req, res) => {
  axios
    .get(`${process.env.prodApiLink}/apps/000000/view/000000`)
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        console.log(
          "/apps/:userId/view/:appId: Test success! Name: " +
            response.data[1] +
            " Description: " +
            response.data[2]
        );
      } else {
        console.log("/apps/:userId/view/:appId: Test failed!");
      }
    })
    .catch(function (error) {
      console.log("/apps/:userId/view/:appId: Test failed!");
    });
};

console.log(
  "Starting tests! Assuming:\nEMAIL: test@test.com\nPASSWORD: test\nUSERID: 000000\nAPI LINK: " +
    process.env.prodApiLink +
    "\n"
);

console.log("/users/auth: Test started!");
auth();
console.log("/apps/:userId/create: Test started!");
createApp();
console.log("/users/create: Test started!");
createUser();
console.log("/users/:userId/apps: Test started!");
getApps();
