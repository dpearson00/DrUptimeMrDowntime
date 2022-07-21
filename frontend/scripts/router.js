const express = require("express");
const router = express.Router();
const rateLimit = require("express-rate-limit");
const homeRoute = require(`${__scriptsDir}/routes/home`);
const viewRoute = require(`${__scriptsDir}/routes/view`);
const loginRoute = require(`${__scriptsDir}/routes/login`);
const dashRoute = require(`${__scriptsDir}/routes/dash`);
const newUserRoute = require(`${__scriptsDir}/routes/newuser`);
const newAppRoute = require(`${__scriptsDir}/routes/newapp`);

const updateApiLimit = rateLimit({
  windowMs: 1 * 60 * 1000,
  max: 1,
  standardHeaders: true,
  legacyHeaders: false,
  message: "This IP has already sent a response in the past minute!",
});
const getPageLimit = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 30,
  standardHeaders: true,
  legacyHeaders: false,
  message:
    "Too many requests created from this IP, please try again after 15 minutes!",
});

function restrictedContent(req, res, next) {
  if (req.session.userId) {
    next();
  } else {
    req.session.error = "Access denied!";
    res.redirect("/login");
  }
}

function redirectLoggedIn(req, res, next) {
  if (req.session.userId) {
    res.redirect("/dash");
  } else {
    next();
  }
}

let routes = (app) => {
  // HOMEPAGE
  router.get("/", redirectLoggedIn, getPageLimit, homeRoute.home);

  // USER HANDLING
  // User Sessions
  router.get("/login", redirectLoggedIn, getPageLimit, loginRoute.login);
  router.post("/auth", getPageLimit, loginRoute.auth);
  router.get("/logout", getPageLimit, loginRoute.logout); // Logout
  // Create User
  router.get("/newuser", redirectLoggedIn, getPageLimit, newUserRoute.newuser);
  router.post("/postnewuser", getPageLimit, newUserRoute.postnewuser);

  // DASHBOARD
  // Logged in user dashboard
  router.get("/dash", restrictedContent, dashRoute.dash);
  // Get ID for the app details page
  router.get("/dash/view/:id", restrictedContent, getPageLimit, viewRoute.view);
  // Create App
  router.get("/dash/newapp", restrictedContent, getPageLimit, newAppRoute.newapp);
  router.post("/postnewapp", getPageLimit, newAppRoute.postnewapp);
  app.use(router);
};
module.exports = routes;
