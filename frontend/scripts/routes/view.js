const { default: axios } = require("axios");

  /*
  View route
  */
  const view = (req, res) => {
    axios.get(`${__apiLink}/apps/${req.params.id}`)
        .then(function (response) {
          res.render('view.ejs', {name: response.data[1], desc: response.data[2]});
        })
        .catch(function (error) {
          res.render('info.ejs', {title: `Failure!`, desc: `Service ${req.params.id} does not exist in this server's content datastore!`});
        })

  };

  module.exports = { view };