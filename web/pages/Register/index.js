import { useState } from "react";
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
  InputRightElement,
  Select
} from "@chakra-ui/react";
import { FaUserAlt, FaLock } from "react-icons/fa";

const CFaUserAlt = chakra(FaUserAlt);
const CFaLock = chakra(FaLock);

const App = () => {
  const [showPassword1, setShowPassword1] = useState(false);
  const [showPassword2, setShowPassword2] = useState(false);

  const handleShowClick1 = () => setShowPassword1(!showPassword1);
  const handleShowClick2 = () => setShowPassword2(!showPassword2);

  return (
    <Flex
      flexDirection="column"
      width="100wh"
      height="100vh"
      backgroundColor="gray.200"
      justifyContent="center"
      alignItems="center"
    >
      <Stack
        flexDir="column"
        mb="2"
        justifyContent="center"
        alignItems="center"
      >
        <Avatar bg="teal.500" />
        <Heading color="teal.400">Register</Heading>
        <Box minW={{ base: "90%", md: "468px" }}>
          <form>
            <Stack
              spacing={4}
              p="1rem"
              backgroundColor="whiteAlpha.900"
              boxShadow="md"
            >
              <FormControl>
                <InputGroup>
                  <InputLeftElement
                    pointerEvents="none"
                    children={<CFaUserAlt color="gray.300" />}
                  />
                  <Input type="email" placeholder="el. paštas" />
                </InputGroup>
              </FormControl>
              <FormControl>
                <Select placeholder="Pasirinkite fakultetą">
                  <option value="option1">aaaa</option>
                  <option value="option2">bbbb</option>
                  <option value="option3">cccc</option>
                </Select>
              </FormControl>
              <FormControl>
                <Select placeholder="Pasirinkite studijų programą">
                  <option value="option1">aaaa</option>
                  <option value="option2">bbbb</option>
                  <option value="option3">cccc</option>
                </Select>
              </FormControl>
              <FormControl>
                <InputGroup>
                  <InputLeftElement
                    pointerEvents="none"
                    color="gray.300"
                    children={<CFaLock color="gray.300" />}
                  />
                  <Input
                    type={showPassword1 ? "text" : "password"}
                    placeholder="slaptažodis"
                  />
                  <InputRightElement width="4.5rem">
                    <Button h="1.75rem" size="sm" onClick={handleShowClick1}>
                      {showPassword1 ? "Rodyti" : "Slėpti"}
                    </Button>
                  </InputRightElement>
                </InputGroup>
              </FormControl>
              <FormControl>
                <InputGroup>
                  <InputLeftElement
                    pointerEvents="none"
                    color="gray.300"
                    children={<CFaLock color="gray.300" />}
                  />
                  <Input
                    type={showPassword2 ? "text" : "password"}
                    placeholder="pakartokite slaptažodį"
                  />
                  <InputRightElement width="4.5rem">
                    <Button h="1.75rem" size="sm" onClick={handleShowClick2}>
                      {showPassword2 ? "Rodyti" : "Slėpti"}
                    </Button>
                  </InputRightElement>
                </InputGroup>
              </FormControl>
              <Button
                borderRadius={0}
                type="submit"
                variant="solid"
                colorScheme="teal"
                width="full"
              >
                Register
              </Button>
            </Stack>
          </form>
        </Box>
      </Stack>
      <Box>
        Turite paskyrą?{" "}
        <Link color="teal.500" href="../">
          Prisijungti
        </Link>
      </Box>
    </Flex>
  );
};

export default App;