const express = require('express');

const userRoute = require('./routes/user')

const bodyParser = require('body-parser');

const cors = require('cors');

require('./config/connect');



const app = express();

// Utilisez le middleware cors
app.use(cors({
    origin: 'http://localhost:5173' 
  }));

app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.json())

app.use('/user',userRoute)

app.listen(3000 , ()=>{
    console.log("server work!")

});