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
  Flex
} from "@chakra-ui/react";
import { ChevronUpIcon, ChevronDownIcon } from "@chakra-ui/icons";
import makeData from "../data";

const posts = [
  {
    moduleCode: "P170B400",
    moduleName: "Algoritmų sudarymas ir analizė",
    reviews: 5,
    link: <Link href={`/modules/${encodeURIComponent("P170B400")}`}><a>{"Details"}</a></Link>
  },
  {
    moduleCode: "P175B015",
    moduleName: "Programų sistemų inžinerija",
    reviews: 2,
    link: <Link href={`/modules/${encodeURIComponent("P175B015")}`}><a>{"Details"}</a></Link>
  },
  {
    moduleCode: "P175B124",
    moduleName: "Programavimo kalbų teorija",
    reviews: 0,
    link: <Link href={`/modules/${encodeURIComponent("P175B124")}`}><a>{"Details"}</a></Link>
  },
  {
    moduleCode: "P175B304",
    moduleName: "Operacinės sistemos",
    reviews: 11,
    link: <Link href={`/modules/${encodeURIComponent("P175B304")}`}><a>{"Details"}</a></Link>
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
    <Header/>
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

export default function App() {
  const columns = React.useMemo(
    () => [
      {
        Header: "Modules",
        columns: [
          {
            Header: "code",
            accessor: "moduleCode"
          },
          {
            Header: "Name",
            accessor: "moduleName"
          }
        ]
      },
      {
        Header: "Info",
        columns: [
          {
            Header: "Reviews",
            accessor: "reviews"
          },
          {
            Header: "Link",
            accessor: "link"
          }
        ]
      }
    ],
    []
  );

  return (
    <ChakraProvider>
      <CustomTable columns={columns} data={posts} />
    </ChakraProvider>
  );
}

