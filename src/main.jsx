import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import './App.css'
import Copyright from './Components/Copyright.jsx'
import Stats from './Components/Stats.jsx'
import CTA from './Components/CTA.jsx'
import Temoignage from './Components/Temoignage.jsx'
import Footer from './Components/Footer.jsx'
import AnnouncementBar from './App.jsx'
import Features from './Components/Features.jsx'
import Services from './Components/Services.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
 <AnnouncementBar />
 <Stats />
 <Features />
 <Services />
 <CTA />
 <Temoignage />
 < Footer />
 < Copyright />
  </React.StrictMode>,
)
