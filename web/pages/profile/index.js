import React from 'react'
import Header from '../../components/header'
import {
  Flex,
  Heading,
  Input,
  Button,
  InputGroup,
  Stack,
  InputLeftElement,
  chakra,
  Box,
  Link,
  Avatar,
  FormControl,
  FormHelperText,
  InputRightElement,
  Center
} from "@chakra-ui/react";

function index() {
  return (
    <div>
        <Header/>        
        <Center margin={"10 10 10 10"} padding={"100px"}>
          <Box>
            <Avatar size='2xl' name='Dan Abrahmov' src='https://bit.ly/dan-abramov' />
          </Box>
        </Center>
    </div>
  )
}

export default index