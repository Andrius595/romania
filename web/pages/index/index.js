import React from 'react'
import Header from '../../components/header'
import {Flex, Box} from "@chakra-ui/react";

function index() {
  return (
      <Box>
        <Header>
        </Header>
        <Flex alignItems={"center"} margin={"10"}>
            Here will be displayed modules, that will be viewable
        </Flex>
    </Box>
  )
}

export default index