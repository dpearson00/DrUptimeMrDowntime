const fs = require('fs');
var colors = require('colors');

/*
Homepage route
*/
const home = async (req, res) => {
    res.render('home.ejs');
}

module.exports = { home };