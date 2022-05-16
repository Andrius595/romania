import Image from 'next/image'
import React from 'react'
import Header from '../../components/header'
import {Flex, Box, Center} from "@chakra-ui/react";

function index() {
  return (
    <Box>
      <Header/>
    <div className="pt-2" style={{  position: 'relative', width: '100vw', height: '66.66vw'}}>
      <Image 
        src="/web/styles/calendar.png"
        layout="fill"
        objectFit="cover"
      />
    </div>
    </Box>
  )
}

export default index