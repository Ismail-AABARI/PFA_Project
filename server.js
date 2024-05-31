const express = require('express');

const userRoute = require('./routes/user')

const bodyParser = require('body-parser');

require('./config/connect');


const app = express();

app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.json())

app.use('/user',userRoute)

app.listen(3000 , ()=>{
    console.log("server work!")

});