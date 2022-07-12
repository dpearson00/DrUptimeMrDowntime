const { default: axios } = require("axios");
const { json } = require("express");
  /*
  Dashboard route
  */
const dash = (req, res) => {
    // This is using the mock API which starts alongside the main server when configured to via .env
    axios.get(`${__apiLink}/users/apps/${req.session.user}`)
        .then(function (response) {
        var name = JSON.parse(JSON.stringify(response.data[1]));
        var ids = JSON.parse(JSON.stringify(response.data[2]));
    
        var count = Object.keys(ids).length;

        const bundles = new Array();

        for ( var i = 0; i < count; i++ ) {
                bundles.push( [ name[i], ids[i] ] );
        }

        console.log(bundles)
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