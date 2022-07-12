const { default: axios } = require("axios");
const { json } = require("express");
  /*
  Dashboard route
  */
const dash = (req, res) => {
    // This is using the mock API which starts alongside the main server when running `node mockapi.js`
    axios.get(`http://localhost:8081/apps/${req.session.user}`)
        .then(function (response) {
        var name = JSON.parse(JSON.stringify(response.data[1]));
        var ids = JSON.parse(JSON.stringify(response.data[2]));
    
        var count = Object.keys(name[0]).length;

        const bundles = new Array();
        // i < count + 1 is strange logic, but it's a workaround to get it to work.
        for ( var i = 1; i < count + 1; i++ ) {
                bundles.push( [ name[0][i], ids[0][i] ] );
        }

        res.render("dash.ejs", {
            welcome: `Welcome, `+ req.session.user + `!`,
            bundles: bundles,
        });

        })
        .catch(function (error) {
            res.render('info.ejs', {title: `Error getting applications!`, desc: `The API may be down!`});
        })
};

module.exports = { dash };