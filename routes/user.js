const express = require('express');

const router = express.Router();

const User = require('../models/user');

const bcrypt = require('bcrypt');

const jwt = require('jsonwebtoken');

router.post('/registre', async (req, res) => {
    const { email, password } = req.body;

    try {
        const existingUser = await User.findOne({ email });
        if (existingUser) {
            return res.status(400).send({ error: 'User already exists' });
        }

        const salt = bcrypt.genSaltSync(10);
        const hashedPassword = bcrypt.hashSync(password, salt);

        const newUser = new User({ email : email, password: hashedPassword });
        await newUser.save();

        // req.session.email = email; // Stocker l'email dans la session si nécessaire

        // Rediriger l'utilisateur vers main.html après l'enregistrement réussi
        // res.redirect('main.html');
        res.status(200).send({message : 'User registered successfully'});
    } catch (error) {
        res.status(400).send({ error: 'Registration failed', message: error.message });
    }
});

router.post('/login',async (req,res)=>{

    data = req.body;

    user = await User.findOne( {email:data.email} )

    if(!user){
        res.status(400).send('email or password invalid 1!')
    }
    else{

        validPass = bcrypt.compareSync(data.password , user.password)

        if(!validPass){
            res.status(401).send('email or password invalid 2 ! ')
        }
        else{
            payload ={ // c'est un objet qui contient le data , et le lui donne le secret key
                _id :user._id,
                email:user.email,
            }
            token = jwt.sign(payload,user.password)//secret key
            
            res.status(200).send({
                message: 'User logged in successfully',
                mytoken: token,
            });
        }
    }

});


router.get('/getall',(req,res)=>{

    User.find()
        .then(
            (users)=>{
                res.status(200).send(users);
            }
        )
        .catch(
            (err)=>{
                res.status(400).send(err);
            }
        )
    console.log('Data collected successfully');
});

router.get('/byemail/:email', async (req,res)=>{

    try{
        email = req.params.email;
        user = await User.findOne( { email:email } );
        res.status(200).send(user);
    }
    catch(error){
        res.status(400).send(error)
    }

});

router.delete('/delete/:email', async (req,res)=>{

    try{
        email = req.params.email;
        deletedUser = await User.findOneAndDelete( {email:email} )
        res.status(200).send({ message: 'User deleted successfully', deletedUser });
    }
    catch (error) {
        res.status(400).send(error);
    }
    
});

module.exports = router;