  /*
  Dashboard route
  */
const dash = (req, res) => {
    axios.get(`http://lambdathings/apps/${req.session.user}`)
        .then(function (response) {
        // handle success
        console.log(response);
        })
        .catch(function (error) {
        // handle error
        console.log(error);
        })

    res.render('dash.ejs', {welcome: `Welcome, `+ req.session.user + `!`, applications: `LIST OF APPLICATIONS`});
};

module.exports = { dash };