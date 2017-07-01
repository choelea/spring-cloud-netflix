let router = require('express').Router();
var rp = require('request-promise');
const eurekaUtils = require('../utils/eureka-utils');


router.get('/', (req, res) => {
    var model = {};
    var bookmarkServer = eurekaUtils.getBookmarkRootUrl(req.eurekaClient);
    var bookServer = eurekaUtils.getBookRootUrl(req.eurekaClient);

    rp(bookmarkServer + "/jlong/bookmarks")
    .then(function (htmlString) {
        model.bookmarks=JSON.parse(htmlString);
        return rp(bookServer+"/books");
    }).then(function (htmlString){
        model.books=JSON.parse(htmlString);
    }).then((htmlString)=>{
        res.render('default', { title: 'Home Page',model:model });
    }).catch(function (err) {
        console.log(err);
    });
    
    
});


module.exports = router;
