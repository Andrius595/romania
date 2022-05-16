import React from "react";
import { useTable, useSortBy } from "react-table";
import Header from "../../components/header";
import { useRouter } from "next/router";
import Link from "next/link";
import {
  ChakraProvider,
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  Flex,
  Box,
  Center
} from "@chakra-ui/react";
import { ChevronUpIcon, ChevronDownIcon } from "@chakra-ui/icons";
import makeData from "../data";

const posts = [
    {
        name: "Anonymous",
        review: "This module is amazing but requires a lot of time",
    },
    {
        name: "Anonymous",
        review: "Nepatiko, nieko neismokau",    
    },
    {
        name: "Petras",
        review: "Na yra kur tobuleti",
    },
    {
        name: "Jonas",
        review: "Geriausias modulis",
    },
    {
        name: "Anonymous",
        review: "Deja, bet kartosiu si moduli",
    }
  ];


  function CustomTable({ columns, data }) {
    const {
      getTableProps,
      getTableBodyProps,
      headerGroups,
      rows,
      prepareRow
    } = useTable(
      {
        columns,
        data
      },
      useSortBy
    );
  
    const firstPageRows = rows.slice(0, 20);
    return (
      <>
        <Table {...getTableProps()}>
          <Thead>
            {headerGroups.map((headerGroup) => (
              <Tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map((column) => (
                  <Th
                    userSelect="none"
                    {...column.getHeaderProps(column.getSortByToggleProps())}
                  >
                    <Flex alignItems="center">
                      {column.render("Header")}
                      {column.isSorted ? (
                        column.isSortedDesc ? (
                          <ChevronDownIcon ml={1} w={4} h={4} />
                        ) : (
                          <ChevronUpIcon ml={1} w={4} h={4} />
                        )
                      ) : (
                        ""
                      )}
                    </Flex>
                  </Th>
                ))}
              </Tr>
            ))}
          </Thead>
          <Tbody {...getTableBodyProps()}>
            {firstPageRows.map((row, i) => {
              prepareRow(row);
              return (
                <Tr {...row.getRowProps()}>
                  {row.cells.map((cell) => {
                    return (
                      <Td {...cell.getCellProps()}>{cell.render("Cell")}</Td>
                    );
                  })}
                </Tr>
              );
            })}
          </Tbody>
        </Table>
        <br />
      </>
    );
  }
  
const Index = () => {
  const router = useRouter()
  const {id} = router.query

  return(<div>{id}</div>)
}

export default function App() {
    const columns = React.useMemo(
        () => [
          {
            Header: "Review",
            columns: [
              {
                Header: "Name",
                accessor: "name"
              },
              {
                Header: "",
                accessor: "uga"
              }
            ]
          },
          {
            Header: "Info",
            columns: [
              {
                Header: "Review",
                accessor: "review"
              },
            ]
          }
        ],
        []
      );
  return (
      
    <Box>
        <Header>
        </Header>        
        <Center>
            <Center padding="30px">
                <Box>
                    <h1>{Index()}</h1>
                </Box>
            </Center>            
        </Center>
        <CustomTable columns={columns} data={posts} />
    </Box>
  )  
}