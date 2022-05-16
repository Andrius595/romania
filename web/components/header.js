import React from "react";
import Link from 'next/link';
import {
  Box,
  Stack,
  Heading,
  Flex,
  Text,
  Button,
  useDisclosure
} from "@chakra-ui/react";

const Header = (props) => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const handleToggle = () => (isOpen ? onClose() : onOpen());

  return (
    <Flex
      as="nav"
      align="center"
      justify="space-between"
      wrap="wrap"
      padding={2}
      bg="teal.500"
      color="white"
      {...props}
    >
      <Flex align="center" mr={5}>
        <Heading as="h1" size="lg" letterSpacing={"tighter"}>
        <Link href="/index">
                    <a>Romania</a>
                </Link>
        </Heading>
      </Flex>

      <Stack
        direction={{ base: "column", md: "row" }}
        display={{ base: isOpen ? "block" : "none", md: "flex" }}
        width={{ base: "full", md: "auto" }}
        flexGrow={1}
        mt={{ base: 6, md: 2 }}
      >
            <Text>
                <Link href="/modules">
                    <a>Modules</a>
                </Link>
            </Text>
        </Stack>

      <Box
        display={{ base: isOpen ? "block" : "none", md: "block" }}
        mt={{ base: 6, md: 0 }}
      >
        <Button
          href="/profile"
          variant="outline"
          _hover={{ bg: "teal.700", borderColor: "teal.700" }}
        >
          <Link href="/profile">
            <a>Profile</a>
        </Link>
        </Button>
      </Box>
    </Flex>
  );
};

export default Header;
