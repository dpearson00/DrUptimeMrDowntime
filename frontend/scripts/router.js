const express = require("express");
const router = express.Router();
const rateLimit = require('express-rate-limit')
const homeRoute = require(`${__scriptsDir}/routes/home`);

const updateApiLimit = rateLimit({
    windowMs: 1 * 60 * 1000,
    max: 1,
    standardHeaders: true,
    legacyHeaders: false,
    message: 'This IP has already sent a response in the past minute!',
})
const getPageLimit = rateLimit({
    windowMs: 15 * 60 * 1000,
    max: 30,
    standardHeaders: true,
    legacyHeaders: false,
    message: 'Too many requests created from this IP, please try again after 15 minutes!',
})

let routes = (app) => {
    // Home
    router.get("/", getPageLimit, homeRoute.home);
    app.use(router);
};
module.exports = routes;